package com.example.tobi.noticeprogram.dto;

import com.example.tobi.noticeprogram.model.User;
import lombok.Getter;

@Getter
public class MemberCreateRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String id;
    private String password;

    public User toUser() {
        return User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .id(id)
                .password(password)
                .build();
    }
}
