package com.example.tobi.getweather.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
private String userid;
private String password;
private String username;
private String email;
private String phone;
}
