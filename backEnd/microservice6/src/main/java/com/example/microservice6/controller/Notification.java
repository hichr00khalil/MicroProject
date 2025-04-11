package com.example.microservice6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mic6/")

public class Notification {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 6 : Notification ";
    }

}
