package com.cys.controller;

import com.cys.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyuan on 2018/1/24.
 */
@RestController
@RequestMapping("/hello")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/sayHello")
    public String say() throws Exception{
        return testService.say();
    }
}
