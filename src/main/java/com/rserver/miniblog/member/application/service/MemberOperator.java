package com.rserver.miniblog.member.application.service;

import com.rserver.miniblog.member.presentation.dto.request.PasswordUpdateRequest;
import com.rserver.miniblog.member.presentation.dto.request.SignUpRequest;
import com.rserver.miniblog.member.domain.Member;
import com.rserver.miniblog.exception.InvalidTokenException;
import com.rserver.miniblog.member.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rserver.miniblog.member.domain.MemberErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberOperator {

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

    public void updatePassword(Member member, PasswordUpdateRequest requestDto) {
        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), member.getPassword())) {
            throw new InvalidTokenException(PASSWORD_NOT_MISMATCH);
        }

        member.updatePassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

}
