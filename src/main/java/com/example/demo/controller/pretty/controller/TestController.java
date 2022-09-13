package com.example.demo.controller.pretty.controller;

import com.example.demo.controller.pretty.dto.TestDTO;
import com.example.demo.controller.pretty.dto.ValidateGetDTO;
import com.example.demo.controller.pretty.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author WuYingBin
 * date: 2022/7/13
 */
@Slf4j
@RestController(value = "prettyTestController")
@RequestMapping("/pretty")
@Validated
public class TestController {

    private TestService testService;

    @PostMapping("/test-validation")
    public void testValidation(@RequestBody @Validated TestDTO testDTO) {
        this.testService.save(testDTO);
    }

    @GetMapping("/{num}")
    public Integer detail(@PathVariable("num") @Min(1) @Max(20) Integer num) {
        int result = num * num;
        log.info("运算结果: {}", result);
        return result;
    }

    @GetMapping("/getByEmail")
    public TestDTO getByAccount(@RequestParam @NotBlank @Email String email) {
        TestDTO testDTO = new TestDTO();
        testDTO.setEmail(email);
        log.info("账号email: {}", email);
        return testDTO;
    }

    @GetMapping(value = "/returnString"/*, produces = "application/json; charset=UTF-8"*/)
    public String returnString() {
        log.info("success");
        return "success";
    }

    @GetMapping("/test-validate-get-param")
    public String testValidateGetParam(@RequestParam @NotBlank String userName,
                                       @RequestParam @Length(min = 6, max = 20) String password,
                                       @RequestParam @Email String email) {
        log.info("userName: {}, password: {}, email: {}", userName, password, email);
        return "success";
    }

    //上面的请求参数太多，写得有点臃肿，可以优化成以下形式
    @GetMapping("/test-validate-get-dto")
    public String testValidateGetDTO(@Validated ValidateGetDTO validateGetDTO) {
        log.info("数据: {}", validateGetDTO);
        return "success";
    }

    @GetMapping("/test-get-request-body")
    public TestDTO testGetRequestBody(@RequestBody TestDTO testDTO) {
        log.info("获取请求体数据: {}", testDTO);
        return testDTO;
    }

    @Autowired
    public void setTestService(TestService prettyTestService) {
        this.testService = prettyTestService;
    }
}
