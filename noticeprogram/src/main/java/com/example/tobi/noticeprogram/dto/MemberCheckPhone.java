package com.example.tobi.noticeprogram.dto;

import com.example.tobi.noticeprogram.model.User;

public class MemberCheckPhone {
    private String phone;

    public User toUser() {
        return User.builder()
                .phone(phone)
                .build();
    }
}
