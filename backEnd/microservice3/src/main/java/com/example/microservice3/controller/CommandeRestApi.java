package com.example.microservice3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mic3/")
public class CommandeRestApi {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 3 : Commande ";
    }
}
