package com.cys.controller;

import com.cys.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ViewResolver;

/**
 * Created by liyuan on 2018/1/24.
 */
@RestController
@RequestMapping("/hello")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String say() throws Exception{
        return testService.say();
    }

}
