package com.example.board.common.exception.file;

import com.example.board.common.exception.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectoryCreationException extends FileSystemException {
	private static final long serialVersionUID = 1L;
	public final String message;
	public final ErrorCode errorCode;

	public DirectoryCreationException() {
		message = "디렉터리 생성에 실패했습니다.";
		errorCode = ErrorCode.DIRECTORY_CREATE_FAILED;
	}
}
