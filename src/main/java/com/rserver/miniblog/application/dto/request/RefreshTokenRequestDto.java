package com.rserver.miniblog.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RefreshTokenRequestDto {

    @NotBlank
    private String refreshToken;

}
