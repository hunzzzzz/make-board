package com.example.board.common.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
	private final String message;
	private final Map<String, String> errors;
	private final String errorPageTitle;
	private final int statusCode;
	private final ErrorCode errorCode;

	public static ErrorResponse of(String message, ErrorCode errorCode) {
		return ErrorResponse.builder().message(message).errorCode(errorCode).build();
	}

	public static ErrorResponse of(String message, BindingResult bindingResult, ErrorCode errorCode) {
		Map<String, String> errors = bindingResult.getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

		return ErrorResponse.builder()
				.message(message)
				.errors(errors)
				.errorCode(errorCode)
				.build();
	}
	
	public static ErrorResponse of(String message, String errorPageTitle, int errorPageStatusCode) {
		return ErrorResponse.builder()
				.message(message)
				.errorPageTitle(errorPageTitle)
				.statusCode(errorPageStatusCode)
				.build();
	}
}
