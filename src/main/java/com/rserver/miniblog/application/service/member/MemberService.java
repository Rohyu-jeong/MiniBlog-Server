package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.application.dto.request.PasswordUpdateRequestDto;
import com.rserver.miniblog.application.dto.request.SignUpRequestDto;
import com.rserver.miniblog.domain.member.Member;
import com.rserver.miniblog.exception.InvalidTokenException;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create (SignUpRequestDto requestDto) {
        String encodePassword = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.createMember(
                requestDto.getUsername(),
                encodePassword,
                requestDto.getEmail(),
                requestDto.getPhoneNumber()
        );

        memberRepository.save(member);
    }

    public Member find (Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("멤버 정보를 찾을 수가 없습니다."));
    }

    public void updatePassword (Member member, PasswordUpdateRequestDto requestDto) {
        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), member.getPassword())) {
            throw new InvalidTokenException("현재 비밀번호가 일치하지 않습니다.");
        }

        member.updatePassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

}
