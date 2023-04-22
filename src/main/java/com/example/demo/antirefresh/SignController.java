package com.example.demo.antirefresh;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WuYingBin
 * date: 2023/4/22
 */
@RestController
@RequestMapping("/sign")
public class SignController {
    @GetMapping("/test")
    public Double signApiTest() {
        return 80.9;
    }
}
