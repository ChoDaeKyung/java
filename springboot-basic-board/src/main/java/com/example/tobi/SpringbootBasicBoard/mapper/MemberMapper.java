package com.example.tobi.SpringbootBasicBoard.mapper;

import com.example.tobi.SpringbootBasicBoard.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void signUp(Member member);
//    Member signIn(String userId);
}
