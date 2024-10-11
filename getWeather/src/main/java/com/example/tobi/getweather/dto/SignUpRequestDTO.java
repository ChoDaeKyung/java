package com.example.tobi.getweather.dto;

import com.example.tobi.getweather.model.User;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class SignUpRequestDTO {
private String userid;
private String password;
private String username;
private String email;
private String phone;

    public User toMember(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .userid(userid)
                .password(bCryptPasswordEncoder.encode(password))
                .username(username)
                .email(email)
                .phone(phone)
                .build();
    }
}
