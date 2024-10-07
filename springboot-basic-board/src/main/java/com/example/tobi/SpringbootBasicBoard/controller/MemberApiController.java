package com.example.tobi.SpringbootBasicBoard.controller;

import com.example.tobi.SpringbootBasicBoard.dto.SignInRequestDTO;
import com.example.tobi.SpringbootBasicBoard.dto.SignInResponseDTO;
import com.example.tobi.SpringbootBasicBoard.dto.SignUpRequestDTO;
import com.example.tobi.SpringbootBasicBoard.dto.SignUpResponseDTO;
import com.example.tobi.SpringbootBasicBoard.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        memberService.signUp(signUpRequestDTO.toMember());
        return ResponseEntity.ok(
                SignUpResponseDTO.builder()
                        .url("/member/login")
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponseDTO>
    signIn(@RequestBody SignInRequestDTO signInRequestDTO, HttpSession session) {
        return ResponseEntity.ok(
                memberService.signIn(signInRequestDTO.toMember(), session)
        );
    }

}
