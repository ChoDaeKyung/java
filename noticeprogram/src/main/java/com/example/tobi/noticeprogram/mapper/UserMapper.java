package com.example.tobi.noticeprogram.mapper;

import com.example.tobi.noticeprogram.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    User login(User user);
}
