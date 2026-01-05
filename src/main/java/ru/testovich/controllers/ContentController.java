package ru.testovich.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Hidden;

@Controller
@Hidden
public class ContentController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/myTests")
    public String myTests() {
        return "myTests";
    }
    
    @GetMapping("/auth/register")
    public String register() {
        return "register";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "login";
    }
}
