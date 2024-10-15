package com.example.tobi.team3.controller;

import com.example.tobi.team3.dto.SignUpRequestDTO;
import com.example.tobi.team3.dto.SignUpResponseDTO;
import com.example.tobi.team3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public ResponseEntity<SignUpResponseDTO> signUp(
            @RequestBody SignUpRequestDTO signUpRequestDTO) {
        userService.signUp(signUpRequestDTO.toMember(bCryptPasswordEncoder));
        return ResponseEntity.ok(
                SignUpResponseDTO.builder()
                        .url("/main/login")
                        .build()
        );
    }
}