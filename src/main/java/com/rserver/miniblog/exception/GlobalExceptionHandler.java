package com.rserver.miniblog.exception;

import com.rserver.miniblog.application.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
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

        return ApiResponse.error(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    @ExceptionHandler(BaseErrorException.class)
    public ApiResponse<Void> handleCustomExceptions(BaseErrorException ex) {
        return ApiResponse.error(ex.getErrorMessage().getStatus().value(), ex.getErrorMessage().getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ApiResponse<Void> handleBadCredentialsException(BadCredentialsException ex) {
        return ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), "로그인 정보가 일치하지 않습니다.");
    }


    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleAllExceptions(Exception ex) {
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부 서버 오류가 발생했습니다.");
    }
}
