package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.application.dto.request.MemberUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.PasswordUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.SignUpRequestDto;
import com.rserver.miniblog.application.dto.response.MemberResponseDto;
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

    public void register(SignUpRequestDto requestDto) {
        duplicateCheck.validateForRegistration(requestDto);
        memberService.create(requestDto);
    }

    public void addNickname(Long memberId, String nickname) {
        duplicateCheck.checkNickname(nickname);
        Member member = memberService.find(memberId);

        member.updateNickname(nickname);
    }

    public void updatePassword(Long memberId, PasswordUpdateRequestDto requestDto) {
        Member member = memberService.find(memberId);
        memberService.updatePassword(member, requestDto);
    }

    public MemberResponseDto getMemberInfo(Long memberId) {
        Member member = memberService.find(memberId);

        return MemberResponseDto.of(member.getUsername(), member.getNickname(), member.getEmail(), member.getPhoneNumber());
    }

    public MemberResponseDto updateMemberInfo(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = memberService.find(memberId);

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

        return MemberResponseDto.of(member.getUsername(), requestDto.getNickname(), requestDto.getEmail(), requestDto.getPhoneNumber());
    }

}
