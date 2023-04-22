package com.example.demo.antirefresh;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.util.SortedMap;

/**
 * @author WuYingBin
 * date: 2023/4/22
 */
@Slf4j
@UtilityClass
public class SignUtil {
    /**
     * 验证签名
     * 验证算法：把timestamp + JsonUtil.object2Json(SortedMap)合成字符串，然后MD5
     */
    @SneakyThrows
    public boolean verifySign(SortedMap<String, String> map, RequestHeader requestHeader) {
        String params = requestHeader.getNonce() + requestHeader.getTimestamp() + JSON.toJSONString(map);
        return verifySign(params, requestHeader);
    }

    /**
     * 验证签名
     */
    public boolean verifySign(String params, RequestHeader requestHeader) {
        String clientSign = requestHeader.getSign();
        log.info("客户端签名: {}", clientSign);
        if (StrUtil.isEmpty(params)) {
            return false;
        }
        log.info("客户端上传内容: {}", params);
        String serverSign = DigestUtils.md5DigestAsHex(params.getBytes()).toUpperCase();
        log.info("服务端签名结果: {}", serverSign);
        return clientSign.equalsIgnoreCase(serverSign);
    }
}
