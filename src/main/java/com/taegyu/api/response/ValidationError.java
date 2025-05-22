package com.taegyu.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 유효성 검사 오류를 나타내는 클래스입니다.
 * 필드명, 오류 메시지, 거부된 값을 포함합니다.
 */
@Getter
@Setter
@Builder
public class ValidationError {
    /**
     * 오류가 발생한 필드명
     */
    private String field;

    /**
     * 오류 메시지
     */
    private String message;

    /**
     * 거부된 값
     */
    private Object rejectedValue;

    /**
     * 유효성 검사 오류를 생성합니다.
     *
     * @param field 오류가 발생한 필드명
     * @param message 오류 메시지
     * @param rejectedValue 거부된 값
     */
    public ValidationError(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    /**
     * 단일 필드에 대한 유효성 검사 오류 목록을 생성합니다.
     *
     * @param field 오류가 발생한 필드명
     * @param message 오류 메시지
     * @param rejectedValue 거부된 값
     * @return 유효성 검사 오류 목록
     */
    public static List<ValidationError> of(String field, String message, Object rejectedValue) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError(field, message, rejectedValue));
        return errors;
    }

    /**
     * 단일 필드에 대한 유효성 검사 오류 목록을 생성합니다.
     * 거부된 값은 null로 설정됩니다.
     *
     * @param field 오류가 발생한 필드명
     * @param message 오류 메시지
     * @return 유효성 검사 오류 목록
     */
    public static List<ValidationError> of(String field, String message) {
        return of(field, message, null);
    }
} 