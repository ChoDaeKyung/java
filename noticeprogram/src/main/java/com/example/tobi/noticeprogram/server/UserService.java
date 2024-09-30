package com.example.tobi.noticeprogram.server;

import com.example.tobi.noticeprogram.mapper.UserMapper;
import com.example.tobi.noticeprogram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void createUser(User user) {
        userMapper.insertUser(user);
    }

    public boolean login(User user) {
        User result = userMapper.login(user);

        return result != null;
    }
}