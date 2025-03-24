package com.rserver.miniblog.auth.presentation.dto.internal;

import com.rserver.miniblog.member.domain.LoginType;
import com.rserver.miniblog.member.domain.Member;
import com.rserver.miniblog.member.domain.Role;
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
