package com.rserver.miniblog.application.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberResponseDto {

    private final String username;
    private final String nickname;
    private final String email;
    private final String phoneNumber;

    public static MemberResponseDto of (String username, String nickname, String email, String phoneNumber) {
        return new MemberResponseDto(username, nickname, email, phoneNumber);
    }

}
