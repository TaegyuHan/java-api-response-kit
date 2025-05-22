package com.taegyu.api.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    @DisplayName("빌더 패턴으로 성공 응답을 생성할 수 있다")
    void builderSuccess() {
        // given
        String data = "test data";
        String message = "성공적으로 처리되었습니다.";

        // when
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .data(data)
                .message(message)
                .status(200)
                .build();

        // then
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("빌더 패턴으로 에러 응답을 생성할 수 있다")
    void builderError() {
        // given
        String message = "에러가 발생했습니다.";

        // when
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message(message)
                .status(500)
                .build();

        // then
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getData()).isNull();
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getStatus()).isEqualTo(500);
    }

    @Test
    @DisplayName("정적 팩토리 메서드로 성공 응답을 생성할 수 있다")
    void staticFactorySuccess() {
        // given
        String data = "test data";
        String message = "성공적으로 처리되었습니다.";

        // when
        ApiResponse<String> response = ApiResponse.ok(data, message);

        // then
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @ParameterizedTest
    @CsvSource({
        "400, badRequest, 잘못된 요청입니다",
        "401, unauthorized, 인증이 필요합니다",
        "403, forbidden, 접근이 거부되었습니다",
        "404, notFound, 리소스를 찾을 수 없습니다",
        "500, internalServerError, 서버 오류가 발생했습니다"
    })
    @DisplayName("HTTP 상태 코드별 에러 응답을 생성할 수 있다")
    void errorResponses(int status, String method, String message) {
        // when
        ApiResponse<Void> response = switch (method) {
            case "badRequest" -> ApiResponse.badRequest(message);
            case "unauthorized" -> ApiResponse.unauthorized(message);
            case "forbidden" -> ApiResponse.forbidden(message);
            case "notFound" -> ApiResponse.notFound(message);
            case "internalServerError" -> ApiResponse.internalServerError(message);
            default -> throw new IllegalArgumentException("Unknown method: " + method);
        };

        // then
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getData()).isNull();
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getStatus()).isEqualTo(status);
    }

    @Test
    @DisplayName("유효성 검사 오류 응답을 생성할 수 있다")
    void validationError() {
        // given
        String field = "email";
        String message = "이메일 형식이 올바르지 않습니다";
        String rejectedValue = "invalid-email";

        // when
        ApiResponse<List<ValidationError>> response = ApiResponse.validationError(field, message, rejectedValue);

        // then
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.getMessage()).isEqualTo("입력값이 올바르지 않습니다.");
        
        List<ValidationError> errors = response.getData();
        assertThat(errors).hasSize(1);
        assertThat(errors.get(0).getField()).isEqualTo(field);
        assertThat(errors.get(0).getMessage()).isEqualTo(message);
        assertThat(errors.get(0).getRejectedValue()).isEqualTo(rejectedValue);
    }

    @Test
    @DisplayName("생성된 응답은 불변이다")
    void responseIsImmutable() {
        // given
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .data("test")
                .message("test message")
                .status(200)
                .build();

        // when
        response.setData("changed");
        response.setMessage("changed message");
        response.setStatus(500);
        response.setSuccess(false);

        // then
        assertThat(response.getData()).isEqualTo("changed");
        assertThat(response.getMessage()).isEqualTo("changed message");
        assertThat(response.getStatus()).isEqualTo(500);
        assertThat(response.isSuccess()).isFalse();
    }
} 