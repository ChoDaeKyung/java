package com.example.tobi.getweather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class UserController {

    @GetMapping("/join")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("/login")
    public String signIn() {
        return "login";
    }
}
