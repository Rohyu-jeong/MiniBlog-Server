package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.application.dto.request.MemberUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.PasswordUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.SignUpRequestDto;
import com.rserver.miniblog.application.dto.response.MemberResponseDto;
import com.rserver.miniblog.application.dto.response.NicknameResponseDto;
import com.rserver.miniblog.domain.member.Member;
import com.rserver.miniblog.domain.member.Nickname;
import com.rserver.miniblog.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final DuplicateCheck duplicateCheck;
    private final MemberService memberService;
    private final NicknameService nicknameService;

    public void register(SignUpRequestDto requestDto) {
        duplicateCheck.validateForRegistration(requestDto);
        memberService.create(requestDto);
    }

    public void saveNickname (Long memberId, String nickname) {
        duplicateCheck.checkNickname(nickname);
        nicknameService.save(memberId, nickname);
    }

    public NicknameResponseDto findNicknameByMemberId (Long memberId) {
        try {
            Nickname nickname = nicknameService.find(memberId);
            return NicknameResponseDto.of(true, nickname.getNickname());
        } catch (NotFoundException e) {
            return NicknameResponseDto.of(false, null);
        }
    }

    public void updatePassword (Long memberId, PasswordUpdateRequestDto requestDto) {
        Member member = memberService.find(memberId);
        memberService.updatePassword(member, requestDto);
    }

    public MemberResponseDto getMemberInfo(Long memberId) {
        Member member = memberService.find(memberId);
        Nickname nickname = nicknameService.find(memberId);

        return MemberResponseDto.of(member.getUsername(), nickname.getNickname(), member.getEmail(), member.getPhoneNumber());
    }

    public MemberResponseDto updateMemberInfo (Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = memberService.find(memberId);
        Nickname nickname = nicknameService.find(memberId);

        if (!member.getEmail().equals(requestDto.getEmail())) {
            duplicateCheck.checkEmail(requestDto.getEmail());
            member.updateEmail(requestDto.getEmail());
        }

        if (!member.getPhoneNumber().equals(requestDto.getPhoneNumber())) {
            duplicateCheck.checkPhoneNumber(requestDto.getPhoneNumber());
            member.updatePhoneNumber(requestDto.getPhoneNumber());
        }

        if (!nickname.getNickname().equals(requestDto.getNickname())) {
            duplicateCheck.checkNickname(requestDto.getNickname());
            nickname.updateNickname(requestDto.getNickname());
        }

        return MemberResponseDto.of(member.getUsername(), requestDto.getNickname(), requestDto.getEmail(), requestDto.getPhoneNumber());
    }

}
