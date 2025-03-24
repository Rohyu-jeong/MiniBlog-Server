package com.rserver.miniblog.auth.presentation.dto.internal;

import com.rserver.miniblog.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class IssueTokenData {

    private final Member member;
    private final String deviceInfo;
    private final String ipAddress;

    public static IssueTokenData from(Member member, String deviceInfo, String ipAddress) {
        return new IssueTokenData(member, deviceInfo, ipAddress);
    }

}
