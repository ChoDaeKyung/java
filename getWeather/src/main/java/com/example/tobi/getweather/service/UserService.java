package com.example.tobi.getweather.service;

import com.example.tobi.getweather.mapper.UserMapper;
import com.example.tobi.getweather.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void signUp(User user) {
        userMapper.signUp(user);
    }

}
