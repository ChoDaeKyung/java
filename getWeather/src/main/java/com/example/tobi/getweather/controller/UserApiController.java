package com.example.tobi.getweather.controller;

import com.example.tobi.getweather.dto.SignUpRequestDTO;
import com.example.tobi.getweather.dto.SignUpResponseDTO;
import com.example.tobi.getweather.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        System.out.println("회원가입");
        System.out.println(signUpRequestDTO.getUsername());
        System.out.println(signUpRequestDTO.getPassword());
        userService.signUp(signUpRequestDTO.toMember(bCryptPasswordEncoder));
        return ResponseEntity.ok(
                SignUpResponseDTO.builder()
                        .url("/weather/login")
                        .build()
        );
    }
}
