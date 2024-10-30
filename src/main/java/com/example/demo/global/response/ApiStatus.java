package com.example.demo.global.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ApiStatus {

	// 성공 응답
	OK(HttpStatus.OK, "COM-000", "Ok"),

	// 서버 에러
	EXCEPTION_OCCUR(HttpStatus.INTERNAL_SERVER_ERROR, "DBG-500", "Something went wrong."),
	RUNTIME_EXCEPTION_OCCUR(HttpStatus.INTERNAL_SERVER_ERROR, "DBG-501", "Something went wrong."),

	// 멤버 에러
	MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEM-001", "Member not found."),
	MEMBER_NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "MEM-009", "Nickname is duplicated. Try again."),

	// 로그인 에러
	ACCESS_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "ACT-001", "AccessToken not found."),
	REFRESH_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "RFT-001", "RefreshToken not found."),
	MEMBER_LOGIN_SESSION_EXPIRED(HttpStatus.BAD_REQUEST, "MSE-001", "Member login session expired."),
	OAUTH_ATTRIBUTE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ATH-001", "Cannot find OAuth attribute.")
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
