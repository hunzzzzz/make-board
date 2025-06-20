package com.example.board.common.exception.post;

import org.springframework.http.HttpStatus;

import com.example.board.common.exception.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int statusCode;
	private final String message;
	private final ErrorCode errorCode;

	public PostAccessException(String message, ErrorCode errorCode) {
		super(message);
		this.statusCode = HttpStatus.FORBIDDEN.value();
		this.message = message;
		this.errorCode = errorCode;
	}

}
