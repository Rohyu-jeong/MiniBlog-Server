package com.rserver.miniblog.application.dto.internal;

import com.rserver.miniblog.domain.member.Member;
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
