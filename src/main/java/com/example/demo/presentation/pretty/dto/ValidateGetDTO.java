package com.example.demo.presentation.pretty.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Get请求中多参数把它们封装成一个对象
 *
 * @author WuYingBin
 * date: 2022/7/25
 */
@Data
public class ValidateGetDTO {
    @NotBlank
    private String userName;
    @NotBlank
    @Length(min = 6, max = 20)
    private String password;
    @NotNull
    @Email
    private String email;
}
