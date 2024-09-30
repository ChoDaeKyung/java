package com.example.tobi.noticeprogram.controller;

import com.example.tobi.noticeprogram.dto.MemberCreateRequestDTO;
import com.example.tobi.noticeprogram.dto.MemberLoginRequestDTO;
import com.example.tobi.noticeprogram.server.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestBody MemberLoginRequestDTO request) {
        boolean loginSuccess = userService.login(request.toUser());

        if (loginSuccess) {
            return "redirect:/main/contents";
        } else {
            return "redirect:/main";
        }
    }

    @PostMapping("/register")
    public String createUser(@RequestBody MemberCreateRequestDTO request) {
        userService.createUser( request.toUser() );
        return "redirect:/main";
    }

    @GetMapping("/register")
    public String registPage() {
        return "register";
    }
}
