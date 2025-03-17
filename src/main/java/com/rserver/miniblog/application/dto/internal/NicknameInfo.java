package com.rserver.miniblog.application.dto.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NicknameInfo {

    private final boolean hasNickname;
    private final String nickname;

    public static NicknameInfo of(boolean hasNickname, String nickname) {
        return new NicknameInfo(hasNickname, nickname);
    }

}
