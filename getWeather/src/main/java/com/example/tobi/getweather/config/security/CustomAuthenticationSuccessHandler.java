package com.example.tobi.getweather.config.security;

import com.example.tobi.getweather.dto.SignInResponseDTO;
import com.example.tobi.getweather.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();// CustomUserDetails 가져오기
        User user = userDetails.getUser();

        HttpSession session = request.getSession();
        session.setAttribute("userid", user.getUserid());
        session.setAttribute("username", user.getUsername());

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=utf-8");

        SignInResponseDTO build = SignInResponseDTO.builder()
                .isLoggedIn(true)
                .message("로그인 성공")
                .url("/")
                .userid(user.getUserid())
                .username(user.getUsername())
                .build();

        response.getWriter().write(
                objectMapper.writeValueAsString(build)
        );
    }
}
