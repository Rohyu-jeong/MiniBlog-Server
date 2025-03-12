package com.rserver.miniblog.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NicknameResponseDto {

    private final boolean hasNickname;
    private final String nickname;

    public static NicknameResponseDto of (boolean hasNickname, String nickname) {
        return new NicknameResponseDto(hasNickname, nickname);
    }

}
