package com.rserver.miniblog.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rserver.miniblog.auth.presentation.dto.AuthToken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    private final AuthToken authToken;
    private final String nickname;

    public static LoginResponse of(AuthToken authToken, String nickname) {
        return new LoginResponse(authToken, nickname);
    }

}
