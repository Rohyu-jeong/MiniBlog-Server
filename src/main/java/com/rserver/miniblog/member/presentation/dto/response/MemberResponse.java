package com.rserver.miniblog.member.presentation.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberResponse {

    private final String username;
    private final String nickname;
    private final String email;
    private final String phoneNumber;

    public static MemberResponse of(String username, String nickname, String email, String phoneNumber) {
        return new MemberResponse(username, nickname, email, phoneNumber);
    }

}
