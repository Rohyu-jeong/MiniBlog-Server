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

    @ExceptionHandler(InvalidTokenException.class)
    public ApiResponse<Void> handleInvalidTokenException(InvalidTokenException ex) {
        return ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), "인증 정보가 유효하지 않습니다.");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ApiResponse<Void> handleUnauthorizedException(UnauthorizedException ex) {
        return ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), "인증 정보가 유효하지 않습니다.");
    }

    @ExceptionHandler(DuplicateException.class)
    public ApiResponse<Void> handleDuplicateException(DuplicateException ex) {
        return ApiResponse.error(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ApiResponse<Void> handleNotFoundException(NotFoundException ex) {
        return ApiResponse.error(HttpStatus.NOT_FOUND.value(), "요청한 정보를 찾을 수 없습니다.");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ApiResponse<Void> handleBadCredentialsException(BadCredentialsException ex) {
        return ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), "로그인 정보가 일치하지 않습니다.");
    }

    @ExceptionHandler(BadRequestException.class)
    public ApiResponse<Void> handleBadRequestException(BadRequestException ex) {
        return ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(ImageUploadException.class)
    public ApiResponse<Void> handleImageUploadException(ImageUploadException ex) {
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "이미지 업로드 실패: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleAllExceptions(Exception ex) {
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부 서버 오류가 발생했습니다.");
    }
}
