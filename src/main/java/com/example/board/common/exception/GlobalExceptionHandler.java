package com.example.board.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.example.board.common.exception.file.FileSystemException;
import com.example.board.common.exception.post.PostAccessException;
import com.example.board.common.exception.post.PostNotFoundException;
import com.example.board.common.exception.user.EmailDuplicateException;
import com.example.board.common.exception.user.LoginException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// 유효성 검사 실패
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorResponse error = ErrorResponse.of(null, e.getBindingResult(), ErrorCode.VALIDATION_FAILED);

		return ResponseEntity.badRequest().body(error);
	}
	
	// 이메일 중복 시
	@ExceptionHandler(EmailDuplicateException.class)
	private ResponseEntity<ErrorResponse> handleEmailDuplicateException(EmailDuplicateException e) {
		ErrorResponse error = ErrorResponse.of(e.getMessage(), e.getErrorCode());

		return ResponseEntity.badRequest().body(error);
	}
	
	// 로그인 정보 불일치
	@ExceptionHandler(LoginException.class)
	private ResponseEntity<ErrorResponse> handleLoginException(LoginException e) {
		ErrorResponse error = ErrorResponse.of(e.getMessage(), e.getErrorCode());

		return ResponseEntity.badRequest().body(error);
	}

	// 파일 용량 초과
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException() {
		ErrorResponse error = ErrorResponse.of("업로드 가능한 파일의 최대 크기는 10MB입니다.", ErrorCode.EXCEEDED_MAX_FILE_SIZE);

		return ResponseEntity.badRequest().body(error);
	}
	
	// 파일 관련 에러
	@ExceptionHandler(FileSystemException.class)
	ResponseEntity<ErrorResponse> handleFileSystemException(FileSystemException e) {
		ErrorResponse error = ErrorResponse.of(e.getMessage(), e.errorCode);
	
		return ResponseEntity.internalServerError().body(error);
	}
	
	// 삭제된 게시글에 접근 시
	@ExceptionHandler(PostAccessException.class)
	ResponseEntity<ErrorResponse> handlePostAccessException(PostAccessException e) {
		ErrorResponse error = ErrorResponse.of(e.message, "접근 권한이 없습니다", e.statusCode);
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	// 존재하지 않는 게시글에 접근 시
	@ExceptionHandler(PostNotFoundException.class)
	ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
		ErrorResponse error = ErrorResponse.of(e.message, "페이지를 찾을 수 없습니다", e.statusCode);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}
