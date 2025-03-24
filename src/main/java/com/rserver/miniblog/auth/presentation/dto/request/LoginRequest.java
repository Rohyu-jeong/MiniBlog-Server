package com.rserver.miniblog.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {

    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String username;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private String password;

    @NotBlank
    private String deviceInfo;

}
