package com.example.tobi.noticeprogram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDTO {
    private String name;
    private String email;
    private String phone;
    private String id;
    private String password;
}
