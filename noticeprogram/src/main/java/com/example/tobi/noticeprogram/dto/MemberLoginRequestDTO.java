package com.example.tobi.noticeprogram.dto;

import com.example.tobi.noticeprogram.model.User;
import lombok.Getter;

@Getter
public class MemberLoginRequestDTO {
    private String id;
    private String password;

    public User toUser() {
        return User.builder()
                .id(id)
                .password(password)
                .build();
    }
}