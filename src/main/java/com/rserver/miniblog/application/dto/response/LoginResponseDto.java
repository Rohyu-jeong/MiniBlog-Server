package com.rserver.miniblog.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rserver.miniblog.application.dto.AuthToken;
import com.rserver.miniblog.application.dto.internal.NicknameInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {

    private final AuthToken authToken;
    private final NicknameInfo nicknameInfo;

    public static LoginResponseDto of(AuthToken authToken, NicknameInfo nicknameInfo) {
        return new LoginResponseDto(authToken, nicknameInfo);
    }

}
