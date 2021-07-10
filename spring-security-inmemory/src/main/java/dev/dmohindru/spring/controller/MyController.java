package dev.dmohindru.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/user")
    public String users() {
        return "Welcome user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome admin";
    }
}
