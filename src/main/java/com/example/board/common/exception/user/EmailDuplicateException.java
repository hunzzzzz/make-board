package com.example.board.common.exception.user;

public class EmailDuplicateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmailDuplicateException(String message) {
		super(message);
	}

}
