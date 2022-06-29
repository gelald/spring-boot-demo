package com.example.demo.serialize.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author WuYingBin
 * Date 2022/6/29
 */
@ToString
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 26L;

    private String name;
    private Integer age;
    private String gender;
    private static String signature = "你眼中的世界就是你自己的样子";

    public static String getSignature() {
        return signature;
    }

    public static void setSignature(String signature) {
        User.signature = signature;
    }

}
