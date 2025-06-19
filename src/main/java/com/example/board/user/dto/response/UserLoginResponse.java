package com.example.board.user.dto.response;

import java.util.UUID;

import com.example.board.user.entity.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponse {
	private UUID userId;
	
	private UserRole role;
	
	private String email;
	
	private String password;
	
	private String name;
}
