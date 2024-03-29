package com.example.demo.antirefresh;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author WuYingBin
 * date: 2023/4/22
 */
@Slf4j
@UtilityClass
public class HttpDataUtil {
    /**
     * post请求处理：获取 Body 参数，转换为SortedMap
     */
    public SortedMap<String, String> getBodyParams(final HttpServletRequest request) throws IOException {
        //inputStream -> byte[] -> String -> SortMap
        byte[] bytes = StreamUtils.copyToByteArray(request.getInputStream());
        String body = new String(bytes, StandardCharsets.UTF_8);
        TypeReference<SortedMap<String, String>> typeRef = new TypeReference<SortedMap<String, String>>() {
        };
        return JSON.parseObject(body, typeRef);
    }


    /**
     * get请求处理：将URL请求参数转换成SortedMap
     */
    public static SortedMap<String, String> getUrlParams(HttpServletRequest request) {
        String param = "";
        SortedMap<String, String> result = new TreeMap<>();

        if (StrUtil.isBlank(request.getQueryString())) {
            return result;
        }

        try {
            param = URLDecoder.decode(request.getQueryString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String[] params = param.split("&");
        for (String s : params) {
            String[] array = s.split("=");
            result.put(array[0], array[1]);
        }
        return result;
    }
}
