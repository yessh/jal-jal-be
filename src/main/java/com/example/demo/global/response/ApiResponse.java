package com.example.demo.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(ApiStatus.OK.getCode(), ApiStatus.OK.getMessage(), null);
    }

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> ok(T result) {
        return new ApiResponse<>(ApiStatus.OK.getCode(), ApiStatus.OK.getMessage(), result);
    }

    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> fail(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> fail(ApiStatus apiStatus) {
        return new ApiResponse<>(apiStatus.getCode(), apiStatus.getMessage(), null);
    }
}