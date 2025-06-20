package com.example.board.common.exception.file;

import com.example.board.common.exception.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileSystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public final String message;
	public final ErrorCode errorCode;

	public FileSystemException() {
		this.message = "파일 시스템에 에러가 발생했습니다.";
		errorCode = ErrorCode.FILE_SYSTEM_ERROR;
	}

}
