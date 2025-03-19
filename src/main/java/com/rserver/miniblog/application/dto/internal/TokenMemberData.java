package com.rserver.miniblog.application.dto.internal;

import com.rserver.miniblog.domain.member.LoginType;
import com.rserver.miniblog.domain.member.Member;
import com.rserver.miniblog.domain.member.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenMemberData {

    private String username;
    private Long memberId;
    private String email;
    private LoginType type;
    private Role role;
    private String deviceInfo;
    private String ipAddress;

    public static TokenMemberData from(Member member, String deviceInfo, String ipAddress) {
        return TokenMemberData.builder()
                .username(member.getUsername())
                .memberId(member.getId())
                .email(member.getEmail())
                .type(member.getType())
                .role(member.getRole())
                .deviceInfo(deviceInfo)
                .ipAddress(ipAddress)
                .build();
    }

}
