package com.rserver.miniblog.member.application.service;

import com.rserver.miniblog.member.presentation.dto.response.MemberResponse;
import com.rserver.miniblog.member.application.support.MemberReader;
import com.rserver.miniblog.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountReadService {

    private final MemberReader memberReader;

    public MemberResponse getMemberInfo(Long memberId) {
        Member member = memberReader.findMember(memberId);

        return MemberResponse.of(member.getUsername(), member.getNickname(), member.getEmail(), member.getPhoneNumber());
    }

}
