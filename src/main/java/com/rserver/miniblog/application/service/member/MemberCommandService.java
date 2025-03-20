package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.application.dto.request.PasswordUpdateRequest;
import com.rserver.miniblog.application.dto.request.SignUpRequest;
import com.rserver.miniblog.domain.member.Member;
import com.rserver.miniblog.exception.InvalidTokenException;
import com.rserver.miniblog.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rserver.miniblog.domain.member.MemberErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

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
