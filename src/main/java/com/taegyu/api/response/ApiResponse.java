package com.taegyu.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int status;

    public static <T> ApiResponse<T> of(T data, int status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setStatus(status);
        return response;
    }

    public static <T> ApiResponse<T> of(T data, String message, int status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        response.setStatus(status);
        return response;
    }

    public static <T> ApiResponse<T> error(String message, int status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setStatus(status);
        return response;
    }

    // 자주 사용되는 상태 코드에 대한 편의 메서드들
    public static <T> ApiResponse<T> ok(T data) {
        return of(data, 200);
    }

    public static <T> ApiResponse<T> ok(T data, String message) {
        return of(data, message, 200);
    }

    public static <T> ApiResponse<T> created(T data) {
        return of(data, 201);
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return of(data, message, 201);
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        return error(message, 400);
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return error(message, 404);
    }

    public static <T> ApiResponse<T> unauthorized(String message) {
        return error(message, 401);
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        return error(message, 403);
    }

    public static <T> ApiResponse<T> internalServerError(String message) {
        return error(message, 500);
    }

    // 유효성 검사 오류를 위한 편의 메서드들
    public static ApiResponse<List<ValidationError>> validationError(List<ValidationError> errors) {
        ApiResponse<List<ValidationError>> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setData(errors);
        response.setMessage("입력값이 올바르지 않습니다.");
        response.setStatus(400);
        return response;
    }

    public static ApiResponse<List<ValidationError>> validationError(String field, String message) {
        return validationError(ValidationError.of(field, message));
    }

    public static ApiResponse<List<ValidationError>> validationError(String field, String message, Object rejectedValue) {
        return validationError(ValidationError.of(field, message, rejectedValue));
    }
} 