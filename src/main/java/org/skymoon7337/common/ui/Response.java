package org.skymoon7337.common.ui;

import org.skymoon7337.common.domain.exception.ErrorCode;

// API 응답의 표준 포맷을 정의하는 Response 래퍼 클래스
public record Response<T>(Integer code, String message, T value) {

    public static <T> Response<T> ok(T value) {
        return new Response<>(0, "ok", value);
    }

    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}
