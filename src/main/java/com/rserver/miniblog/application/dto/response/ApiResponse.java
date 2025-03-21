package com.rserver.miniblog.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.rserver.miniblog.common.SuccessMessage.*;

@RequiredArgsConstructor
@Getter
public class ApiResponse<T> {

    private final int status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS.getStatus(), SUCCESS.getMessage(), data);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(SUCCESS.getStatus(), SUCCESS.getMessage(), null);
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }

}
