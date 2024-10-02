package com.example.tobi.noticeprogram.dto;

import com.example.tobi.noticeprogram.model.User;

public class MemberCheckEmail {
    private String email;

    public User toUser() {
        return User.builder()
                .email(email)
                .build();
    }
}
