package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.application.dto.request.PasswordUpdateRequest;
import com.rserver.miniblog.application.dto.request.SignUpRequest;
import com.rserver.miniblog.domain.member.Member;
import com.rserver.miniblog.exception.InvalidTokenException;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rserver.miniblog.domain.member.MemberErrorMessage.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(SignUpRequest requestDto) {
        String encodePassword = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.createMember(
                requestDto.getUsername(),
                encodePassword,
                requestDto.getNickname(),
                requestDto.getEmail(),
                requestDto.getPhoneNumber()
        );

        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(MEMBER_NOT_FOUND.getMessage()));
    }

    public String findNickname(String username) {
        return memberRepository.findNicknameByUsername(username)
                .orElseThrow(() -> new NotFoundException(NICKNAME_NOT_FOUND.getMessage()));
    }

    public void updatePassword(Member member, PasswordUpdateRequest requestDto) {
        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), member.getPassword())) {
            throw new InvalidTokenException(PASSWORD_NOT_MISMATCH.getMessage());
        }

        member.updatePassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

}
