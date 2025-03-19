package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.application.dto.request.MemberUpdateRequest;
import com.rserver.miniblog.application.dto.request.PasswordUpdateRequest;
import com.rserver.miniblog.application.dto.request.SignUpRequest;
import com.rserver.miniblog.application.dto.response.MemberResponse;
import com.rserver.miniblog.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final DuplicateCheck duplicateCheck;
    private final MemberService memberService;

    public void register(SignUpRequest requestDto) {
        duplicateCheck.validateForRegistration(requestDto);
        memberService.create(requestDto);
    }

    public void updatePassword(Long memberId, PasswordUpdateRequest requestDto) {
        Member member = memberService.findMember(memberId);
        memberService.updatePassword(member, requestDto);
    }

    public MemberResponse getMemberInfo(Long memberId) {
        Member member = memberService.findMember(memberId);

        return MemberResponse.of(member.getUsername(), member.getNickname(), member.getEmail(), member.getPhoneNumber());
    }

    public MemberResponse updateMemberInfo(Long memberId, MemberUpdateRequest requestDto) {
        Member member = memberService.findMember(memberId);

        if (!member.getEmail().equals(requestDto.getEmail())) {
            duplicateCheck.checkEmail(requestDto.getEmail());
            member.updateEmail(requestDto.getEmail());
        }

        if (!member.getPhoneNumber().equals(requestDto.getPhoneNumber())) {
            duplicateCheck.checkPhoneNumber(requestDto.getPhoneNumber());
            member.updatePhoneNumber(requestDto.getPhoneNumber());
        }

        if (!member.getNickname().equals(requestDto.getNickname())) {
            duplicateCheck.checkNickname(requestDto.getNickname());
            member.updateNickname(requestDto.getNickname());
        }

        return MemberResponse.of(member.getUsername(), requestDto.getNickname(), requestDto.getEmail(), requestDto.getPhoneNumber());
    }

}
