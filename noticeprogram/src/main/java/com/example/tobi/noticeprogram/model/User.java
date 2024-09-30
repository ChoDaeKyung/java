package com.example.tobi.noticeprogram.model;

import com.example.tobi.noticeprogram.dto.MemberResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String name;
    private String email;
    private String phone;
    private String id;
    private String password;

    public MemberResponseDTO toMemberResponseDTO() {
        return MemberResponseDTO.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .id(id)
                .password(password)
                .build();
    }
}
