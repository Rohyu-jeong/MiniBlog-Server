package com.rserver.miniblog.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorMessage {
    MEMBER_NOT_FOUND("멤버 정보를 찾을 수 없습니다."),
    NICKNAME_NOT_FOUND("닉네임을 찾을 수 없습니다."),
    PASSWORD_NOT_MISMATCH("현재 비밀번호가 일치하지 않습니다."),
    USERNAME_DUPLICATE("이미 사용중인 아이디입니다."),
    EMAIL_DUPLICATE("이미 사용중인 이메일입니다."),
    PHONE_DUPLICATE("이미 사용중인 전화번호입니다."),
    NICKNAME_DUPLICATE("이미 사용중인 닉네임입니다.");

    private final String message;
}
