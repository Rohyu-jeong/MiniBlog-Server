package com.rserver.miniblog.member.application.service;

import com.rserver.miniblog.member.presentation.dto.request.MemberUpdateRequest;
import com.rserver.miniblog.member.presentation.dto.request.PasswordUpdateRequest;
import com.rserver.miniblog.member.presentation.dto.request.SignUpRequest;
import com.rserver.miniblog.member.presentation.dto.response.MemberResponse;
import com.rserver.miniblog.member.application.support.DuplicateChecker;
import com.rserver.miniblog.member.application.support.MemberReader;
import com.rserver.miniblog.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountCommandService {

    private final MemberCommandService memberCommandService;
    private final MemberReader memberReader;
    private final DuplicateChecker duplicateChecker;

    public void register(SignUpRequest requestDto) {
        duplicateChecker.validateForRegistration(requestDto);
        memberCommandService.create(requestDto);
    }

    public void updatePassword(Long memberId, PasswordUpdateRequest requestDto) {
        Member member = memberReader.findMember(memberId);
        memberCommandService.updatePassword(member, requestDto);
    }

    public MemberResponse updateMemberInfo(Long memberId, MemberUpdateRequest requestDto) {
        Member member = memberReader.findMember(memberId);

        if (!member.getEmail().equals(requestDto.getEmail())) {
            duplicateChecker.checkEmail(requestDto.getEmail());
            member.updateEmail(requestDto.getEmail());
        }

        if (!member.getPhoneNumber().equals(requestDto.getPhoneNumber())) {
            duplicateChecker.checkPhoneNumber(requestDto.getPhoneNumber());
            member.updatePhoneNumber(requestDto.getPhoneNumber());
        }

        if (!member.getNickname().equals(requestDto.getNickname())) {
            duplicateChecker.checkNickname(requestDto.getNickname());
            member.updateNickname(requestDto.getNickname());
        }

        return MemberResponse.of(member.getUsername(), requestDto.getNickname(), requestDto.getEmail(), requestDto.getPhoneNumber());
    }

}
