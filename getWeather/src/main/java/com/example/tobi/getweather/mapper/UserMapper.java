package com.example.tobi.getweather.mapper;

import com.example.tobi.getweather.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void signUp(User user);
    User signIn(String userid);
}
