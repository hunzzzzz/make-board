package com.example.board.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.user.entity.User;

@Mapper
public interface UserMapper {
	/**
	 * 회원가입
	 */
	void signup(User user);
	
	int isUsingEmail(String email);
}
