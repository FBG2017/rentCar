package com.fbg.cn.rent_springboot.web.controller;

import com.fbg.cn.rent_springboot.dao.model.User;
import com.fbg.cn.rent_springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String toString() {
        return "hello world";
    }

    @Autowired
    private TestService testService;
    @GetMapping("/user")
    public User toUser(){
        User user = testService.findUserById(1);
        return  user;
    }
}
