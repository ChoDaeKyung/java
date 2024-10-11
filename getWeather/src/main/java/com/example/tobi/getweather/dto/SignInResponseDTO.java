package com.example.tobi.getweather.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponseDTO {
    private boolean isLoggedIn;
    private String url;
    private String userid;
    private String username;
    private String message;
}
