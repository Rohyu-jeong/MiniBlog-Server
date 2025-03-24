package com.rserver.miniblog.exception;

import com.rserver.miniblog.common.ApiResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("요청한 값이 올바르지 않습니다.");

        return ApiResponse.error(400, errorMessage);
    }

    @ExceptionHandler(BaseErrorException.class)
    public ApiResponse<Void> handleCustomExceptions(BaseErrorException ex) {
        return ApiResponse.error(ex.getErrorMessage().getStatus(), ex.getErrorMessage().getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ApiResponse<Void> handleBadCredentialsException(BadCredentialsException ex) {
        return ApiResponse.error(401, "로그인 정보가 일치하지 않습니다.");
    }


    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleAllExceptions(Exception ex) {
        return ApiResponse.error(500, "내부 서버 오류가 발생했습니다.");
    }
}
