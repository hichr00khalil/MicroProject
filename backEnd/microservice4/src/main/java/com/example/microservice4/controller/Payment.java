package com.example.microservice4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mic4/")

public class Payment {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 4 : Payment ";
    }
}
