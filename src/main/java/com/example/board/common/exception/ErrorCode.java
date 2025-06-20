package com.example.board.common.exception;

public enum ErrorCode {
	VALIDATION_FAILED,               // 입력값 검증 실패
	
	DUPLICATED_EMAIL,                // 이메일 중복
	INVALID_LOGIN_INFO,              // 로그인 정보 불일치
	
	EXCEEDED_MAX_FILE_SIZE,          // 파일 크기 초과
	FILE_SYSTEM_ERROR,               // 파일 시스템 에러
	DIRECTORY_CREATE_FAILED,         // 디렉터리 생성 실패
	
	INVALID_AUTHORIZATION_HEADER,    // Authorization 헤더에 토큰 정보 없음
	EXPIRED_ATK,                     // ATK 만료
	INVALID_TOKEN,                   // 유효하지 않은 토큰
}
