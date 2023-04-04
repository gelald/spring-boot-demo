package com.example.demo.filter.three;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author WuYingBin
 * date: 2023/4/4
 */
@Component
public class FilterThree implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //执行Filter逻辑
        System.out.println("这是Filter过滤器3号");
        //让请求继续进入Filter链的下一个节点
        chain.doFilter(request, response);
    }
}
