package com.example.tobi.SpringbootBasicBoard.dto;

import com.example.tobi.SpringbootBasicBoard.model.Member;
import lombok.Getter;

@Getter
public class SignUpRequestDTO {
    private String userId;
    private String password;
    private String userName;

    public Member toMember() {
        return Member.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .build();
    }
}
