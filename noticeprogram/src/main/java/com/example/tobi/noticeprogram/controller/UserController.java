package com.example.tobi.noticeprogram.controller;

import com.example.tobi.noticeprogram.dto.*;
import com.example.tobi.noticeprogram.server.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String login(
            HttpSession session,
            Model model
    ) {
        String id = (String) session.getAttribute("id");

        if (id != null) {
            model.addAttribute("username", id);
        }

        return "login";
    }

    @PostMapping
    public String loginExc(
            @RequestParam String id,
            @RequestParam String password,
            HttpSession session
    ) {
        System.out.println(id);
        System.out.println(password);
        String userPassword = userService. checkPassword(id);
        if (userService.isPWmatch(password, userPassword)) {
            session.setAttribute("id", id);
            return "redirect:/main/contents";
        }else{
            return "redirect:/main";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registPage() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@RequestBody MemberCreateRequestDTO request) {
        userService.createUser( request.toUser() );
        return "redirect:/main";
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> createUser(@RequestBody MemberCreateRequestDTO userData) {
//        String email = userData.getEmail();
//        String phone = userData.getPhone();
//
//        boolean isEmailExist = userService.checkemail(email);
//        boolean isPhoneExist = userService.checkphone(phone);
//
//        if (isEmailExist && isPhoneExist) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("이미 사용된 이메일과 전화번호입니다.");
//        }
//
//        if (isEmailExist) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("이미 사용된 이메일입니다.");
//        }
//
//        if (isPhoneExist) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("이미 사용된 전화번호입니다.");
//        }
//
//        userService.createUser( userData.toUser() );
//        return ResponseEntity.ok("회원가입 성공!");
//    }

    @GetMapping("/contents")
    public String contents() {
        return "contents";
    }
}
