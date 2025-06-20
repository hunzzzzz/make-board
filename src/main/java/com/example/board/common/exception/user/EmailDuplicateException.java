package com.example.board.common.exception.user;

import com.example.board.common.exception.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDuplicateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;
	private final String message;

	public EmailDuplicateException() {
		errorCode = ErrorCode.DUPLICATED_EMAIL;
		message = "이미 사용 중인 이메일입니다.";
	}

}
