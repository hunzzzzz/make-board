package com.example.board.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.example.board.common.exception.file.FileStorageException;
import com.example.board.common.exception.post.PostAccessException;
import com.example.board.common.exception.post.PostNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorResponse error = ErrorResponse.of(null, e.getBindingResult());

		return ResponseEntity.badRequest().body(error);
	}

	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException() {
		ErrorResponse error = ErrorResponse.of("업로드 가능한 파일의 최대 크기는 10MB입니다.");

		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(FileStorageException.class)
	ResponseEntity<ErrorResponse> handleFileStorageException(FileStorageException e) {
		ErrorResponse error = ErrorResponse.of(e.getMessage());
	
		return ResponseEntity.internalServerError().body(error);
	}
	
	@ExceptionHandler(PostAccessException.class)
	ResponseEntity<ErrorResponse> handlePostAccessException(PostAccessException e) {
		ErrorResponse error = ErrorResponse.of(e.message, "접근 권한이 없습니다", e.statusCode);
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
		ErrorResponse error = ErrorResponse.of(e.message, "페이지를 찾을 수 없습니다", e.statusCode);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
