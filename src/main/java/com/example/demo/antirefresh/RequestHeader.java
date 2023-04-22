package com.example.demo.antirefresh;

import lombok.Builder;
import lombok.Data;

/**
 * @author WuYingBin
 * date: 2023/4/22
 */
@Data
@Builder
public class RequestHeader {
    /**
     * 签名
     */
    private String sign;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 仅一次有效的随机字符串
     */
    private String nonce;
}