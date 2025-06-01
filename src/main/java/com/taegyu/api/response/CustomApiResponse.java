package com.taegyu.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * API 응답을 위한 기본 클래스입니다.
 * 성공/실패 여부, 메시지, 데이터, HTTP 상태 코드를 포함합니다.
 *
 * @param <T> 응답 데이터의 타입
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomApiResponse<T> {

    /**
     * API 응답 코드.
     */
    private String code;

    /**
     * API 요청의 성공 여부
     */
    private boolean success;

    /**
     * API 응답 메시지
     */
    private String message;

    /**
     * API 응답 데이터
     */
    private T data;

    /**
     * HTTP 상태 코드
     */
    private int status;

    /**
     * 데이터와 상태 코드를 포함한 응답을 생성합니다.
     *
     * @param data 응답 데이터
     * @param status HTTP 상태 코드
     * @param <T> 응답 데이터의 타입
     * @return 생성된 응답 객체
     */
    public static <T> CustomApiResponse<T> of(T data, int status) {
        CustomApiResponse<T> response = new CustomApiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setStatus(status);
        return response;
    }

    /**
     * 데이터, 메시지, 상태 코드를 포함한 응답을 생성합니다.
     *
     * @param data 응답 데이터
     * @param message 응답 메시지
     * @param status HTTP 상태 코드
     * @param <T> 응답 데이터의 타입
     * @return 생성된 응답 객체
     */
    public static <T> CustomApiResponse<T> of(T data, String message, int status) {
        CustomApiResponse<T> response = new CustomApiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        response.setStatus(status);
        return response;
    }

    /**
     * 에러 메시지와 상태 코드를 포함한 에러 응답을 생성합니다.
     *
     * @param message 에러 메시지
     * @param status HTTP 상태 코드
     * @param <T> 응답 데이터의 타입
     * @return 생성된 에러 응답 객체
     */
    public static <T> CustomApiResponse<T> error(String message, int status) {
        CustomApiResponse<T> response = new CustomApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setStatus(status);
        return response;
    }

    /**
     * 200 OK 상태 코드와 함께 데이터를 포함한 응답을 생성합니다.
     *
     * @param data 응답 데이터
     * @param <T> 응답 데이터의 타입
     * @return 생성된 응답 객체
     */
    public static <T> CustomApiResponse<T> ok(T data) {
        return of(data, 200);
    }

    /**
     * 200 OK 상태 코드와 함께 데이터와 메시지를 포함한 응답을 생성합니다.
     *
     * @param data 응답 데이터
     * @param message 응답 메시지
     * @param <T> 응답 데이터의 타입
     * @return 생성된 응답 객체
     */
    public static <T> CustomApiResponse<T> ok(T data, String message) {
        return of(data, message, 200);
    }

    /**
     * 201 Created 상태 코드와 함께 데이터를 포함한 응답을 생성합니다.
     *
     * @param data 응답 데이터
     * @param <T> 응답 데이터의 타입
     * @return 생성된 응답 객체
     */
    public static <T> CustomApiResponse<T> created(T data) {
        return of(data, 201);
    }

    /**
     * 201 Created 상태 코드와 함께 데이터와 메시지를 포함한 응답을 생성합니다.
     *
     * @param data 응답 데이터
     * @param message 응답 메시지
     * @param <T> 응답 데이터의 타입
     * @return 생성된 응답 객체
     */
    public static <T> CustomApiResponse<T> created(T data, String message) {
        return of(data, message, 201);
    }

    /**
     * 400 Bad Request 상태 코드와 함께 에러 메시지를 포함한 응답을 생성합니다.
     *
     * @param message 에러 메시지
     * @param <T> 응답 데이터의 타입
     * @return 생성된 에러 응답 객체
     */
    public static <T> CustomApiResponse<T> badRequest(String message) {
        return error(message, 400);
    }

    /**
     * 404 Not Found 상태 코드와 함께 에러 메시지를 포함한 응답을 생성합니다.
     *
     * @param message 에러 메시지
     * @param <T> 응답 데이터의 타입
     * @return 생성된 에러 응답 객체
     */
    public static <T> CustomApiResponse<T> notFound(String message) {
        return error(message, 404);
    }

    /**
     * 401 Unauthorized 상태 코드와 함께 에러 메시지를 포함한 응답을 생성합니다.
     *
     * @param message 에러 메시지
     * @param <T> 응답 데이터의 타입
     * @return 생성된 에러 응답 객체
     */
    public static <T> CustomApiResponse<T> unauthorized(String message) {
        return error(message, 401);
    }

    /**
     * 403 Forbidden 상태 코드와 함께 에러 메시지를 포함한 응답을 생성합니다.
     *
     * @param message 에러 메시지
     * @param <T> 응답 데이터의 타입
     * @return 생성된 에러 응답 객체
     */
    public static <T> CustomApiResponse<T> forbidden(String message) {
        return error(message, 403);
    }

    /**
     * 500 Internal Server Error 상태 코드와 함께 에러 메시지를 포함한 응답을 생성합니다.
     *
     * @param message 에러 메시지
     * @param <T> 응답 데이터의 타입
     * @return 생성된 에러 응답 객체
     */
    public static <T> CustomApiResponse<T> internalServerError(String message) {
        return error(message, 500);
    }

    /**
     * 유효성 검사 오류 목록을 포함한 응답을 생성합니다.
     *
     * @param errors 유효성 검사 오류 목록
     * @return 생성된 에러 응답 객체
     */
    public static CustomApiResponse<List<ValidationError>> validationError(List<ValidationError> errors) {
        CustomApiResponse<List<ValidationError>> response = new CustomApiResponse<>();
        response.setSuccess(false);
        response.setData(errors);
        response.setMessage("입력값이 올바르지 않습니다.");
        response.setStatus(400);
        return response;
    }

    /**
     * 단일 필드에 대한 유효성 검사 오류를 포함한 응답을 생성합니다.
     *
     * @param field 오류가 발생한 필드명
     * @param message 오류 메시지
     * @return 생성된 에러 응답 객체
     */
    public static CustomApiResponse<List<ValidationError>> validationError(String field, String message) {
        return validationError(ValidationError.of(field, message));
    }

    /**
     * 단일 필드에 대한 유효성 검사 오류와 거부된 값을 포함한 응답을 생성합니다.
     *
     * @param field 오류가 발생한 필드명
     * @param message 오류 메시지
     * @param rejectedValue 거부된 값
     * @return 생성된 에러 응답 객체
     */
    public static CustomApiResponse<List<ValidationError>> validationError(String field, String message, Object rejectedValue) {
        return validationError(ValidationError.of(field, message, rejectedValue));
    }
} 