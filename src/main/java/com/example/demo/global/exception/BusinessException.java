package com.example.demo.global.exception;


import com.example.demo.global.response.ApiStatus;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

	private String code;

	public BusinessException(ApiStatus apiStatus) {
		super(apiStatus.getMessage());
		this.code = apiStatus.getCode();
	}
}
