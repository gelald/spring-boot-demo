package com.example.demo.controller.pretty.controller;

import com.example.demo.controller.pretty.dto.TestDTO;
import com.example.demo.controller.pretty.dto.ValidateGetDTO;
import com.example.demo.controller.pretty.service.TestService;
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
        return num * num;
    }

    @GetMapping("/getByEmail")
    public TestDTO getByAccount(@RequestParam @NotBlank @Email String email) {
        TestDTO testDTO = new TestDTO();
        testDTO.setEmail(email);
        return testDTO;
    }

    @GetMapping(value = "/returnString"/*, produces = "application/json; charset=UTF-8"*/)
    public String returnString() {
        return "success";
    }

    @GetMapping("/test-validate-get-param")
    public String testValidateGetParam(@RequestParam @NotBlank String userName,
                                       @RequestParam @Length(min = 6, max = 20) String password,
                                       @RequestParam @Email String email) {
        System.out.printf("userName: %s, password: %s, email: %s", userName, password, email);
        return "success";
    }

    //上面的请求参数太多，写得有点臃肿，可以优化成以下形式
    @GetMapping("/test-validate-get-dto")
    public String testValidateGetDTO(@Validated ValidateGetDTO validateGetDTO) {
        System.out.println(validateGetDTO);
        return "success";
    }

    @Autowired
    public void setTestService(TestService prettyTestService) {
        this.testService = prettyTestService;
    }
}
