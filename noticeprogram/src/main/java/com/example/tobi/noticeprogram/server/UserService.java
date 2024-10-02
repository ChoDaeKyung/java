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

    public String checkPassword(String id) {
        return userMapper.checkPassword(id);
    }

    public boolean isPWmatch(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

    public boolean checkemail(String email) {
        User user = userMapper.checkemail(email);

        if(user.getEmail().equals(email)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean checkphone(String phone) {
        User user = userMapper.checkphone(phone);

        if(user.getPhone().equals(phone)) {
            return true;
        }else {
            return false;
        }
    }


}