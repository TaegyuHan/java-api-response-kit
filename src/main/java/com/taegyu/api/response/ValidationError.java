package com.taegyu.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError {
    private String field;
    private String message;
    private Object rejectedValue;

    public ValidationError(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public static List<ValidationError> of(String field, String message, Object rejectedValue) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError(field, message, rejectedValue));
        return errors;
    }

    public static List<ValidationError> of(String field, String message) {
        return of(field, message, null);
    }
} 