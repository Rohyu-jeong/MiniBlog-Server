package com.rserver.miniblog.member.application.support;

import com.rserver.miniblog.member.domain.Member;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.member.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.rserver.miniblog.member.domain.MemberErrorMessage.MEMBER_NOT_FOUND;
import static com.rserver.miniblog.member.domain.MemberErrorMessage.NICKNAME_NOT_FOUND;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberReader {

    private final MemberRepository memberRepository;

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(MEMBER_NOT_FOUND));
    }

    public String findNickname(String username) {
        return memberRepository.findNicknameByUsername(username)
                .orElseThrow(() -> new NotFoundException(NICKNAME_NOT_FOUND));
    }

}
