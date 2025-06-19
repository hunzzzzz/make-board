package com.example.board.common.auth;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUser {
	private UUID userId;
	
	private String name;
	
	private List<String> roles;

	public CurrentUser(UUID userId, String name, List<String> roles) {
		this.userId = userId;
		this.name = name;
		this.roles = roles;
	}
	
}
