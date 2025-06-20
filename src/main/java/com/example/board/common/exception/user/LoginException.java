package com.example.board.common.exception.user;

import com.example.board.common.exception.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;
	private final String message;

	public LoginException() {
		errorCode = ErrorCode.INVALID_LOGIN_INFO;
		message = "이메일 혹은 패스워드가 일치하지 않습니다.";
	}

}
