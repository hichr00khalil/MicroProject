package com.example.microservice5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mic5/")
public class Reclamation {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 5 : Reclamation ";
    }

}


