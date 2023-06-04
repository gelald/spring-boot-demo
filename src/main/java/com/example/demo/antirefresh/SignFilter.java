package com.example.demo.antirefresh;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

/**
 * @author WuYingBin
 * date: 2023/4/22
 */
@Slf4j
public class SignFilter implements Filter {
    @Resource
    private RedisUtil redisUtil;

    //从filter配置中获取sign过期时间
    private Long signMaxTime;

    private static final String NONCE_KEY = "x-nonce-";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        log.info("过滤URL:{}", httpRequest.getRequestURI());

        HttpServletRequestWrapper requestWrapper = new SignRequestWrapper(httpRequest);
        //验证请求头是否存在
        if (StrUtil.isEmpty(httpRequest.getHeader("X-Nonce"))
                || ObjectUtils.isEmpty(httpRequest.getHeader("X-Time"))
                || StrUtil.isEmpty(httpRequest.getHeader("X-Sign"))) {
            httpResponse.setStatus(510);
            httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            httpResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
            PrintWriter writer = httpResponse.getWriter();
            writer.write("非法请求，请求头X-Nonce、X-Time、X-Sign都需要设置");
            writer.close();
            return;
        }
        log.info("本次请求请求头完整");
        //构建请求头
        RequestHeader requestHeader = RequestHeader.builder()
                .nonce(httpRequest.getHeader("X-Nonce"))
                .timestamp(Long.parseLong(httpRequest.getHeader("X-Time")))
                .sign(httpRequest.getHeader("X-Sign"))
                .build();

        /*
         * 1.重放验证
         * 判断timestamp时间戳与当前时间是否操过60s（过期时间根据业务情况设置）,如果超过了就提示签名过期。
         */
        long now = System.currentTimeMillis();
        if (now - requestHeader.getTimestamp() > signMaxTime * 1000) {
            httpResponse.setStatus(511);
            httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            httpResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
            PrintWriter writer = httpResponse.getWriter();
            writer.write("时间戳过期，请重新请求");
            writer.close();
            return;
        }
        log.info("本次请求时间戳在正常范围内");

        //2. 判断nonce
        boolean nonceExists = redisUtil.hasKey(NONCE_KEY + requestHeader.getNonce());
        if (nonceExists) {
            //请求重复
            httpResponse.setStatus(512);
            httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            httpResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
            PrintWriter writer = httpResponse.getWriter();
            writer.write("nonce重复，非法重放接口");
            writer.close();
            return;
        } else {
            redisUtil.set(NONCE_KEY + requestHeader.getNonce(), requestHeader.getNonce(), signMaxTime);
        }


        boolean accept;
        SortedMap<String, String> paramMap;
        switch (httpRequest.getMethod()) {
            //一般来说提取参数，不应该这么分类讨论
            //因为即使是POST请求也可以携带RequestParam参数，甚至是PathVariable参数
            //所以这些提取参数的方式应该是组合使用的，虽然GET请求也可以放请求体，但是SpringMVC中不支持解析请求体
            //PathVariable的参数获取是请求到达SpringMVC后才被设置的，由于Filter是Web容器的内容，所以在Filter中是无法获取的
            //最好是在HandlerInterceptor中统一获取参数，无论是QueryString，还是body，还是PathVariable，都可以获取
            //获取PathVariable的方式是：request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)
            case "GET":
                paramMap = HttpDataUtil.getUrlParams(requestWrapper);
                accept = SignUtil.verifySign(paramMap, requestHeader);
                break;
            case "POST":
                paramMap = HttpDataUtil.getBodyParams(requestWrapper);
                accept = SignUtil.verifySign(paramMap, requestHeader);
                break;
            default:
                accept = true;
                break;
        }
        if (accept) {
            filterChain.doFilter(requestWrapper, servletResponse);
        } else {
            httpResponse.setStatus(513);
            httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            httpResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
            PrintWriter writer = httpResponse.getWriter();
            writer.write("签名不对应，非法篡改接口");
            writer.close();
        }

    }


    @Override
    public void init(FilterConfig filterConfig) {
        String signTime = filterConfig.getInitParameter("signMaxTime");
        signMaxTime = Long.parseLong(signTime);
    }
}