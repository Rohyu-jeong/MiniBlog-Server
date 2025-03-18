package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.application.dto.request.SignUpRequest;
import com.rserver.miniblog.exception.DuplicateException;
import com.rserver.miniblog.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DuplicateCheck {

    private final MemberRepository memberRepository;

    public void validateForRegistration(SignUpRequest requestDto) throws DuplicateException {
        checkUsername(requestDto.getUsername());
        checkNickname(requestDto.getNickname());
        checkEmail(requestDto.getEmail());
        checkPhoneNumber(requestDto.getPhoneNumber());
    }

    public void checkUsername(String username) {
        if(memberRepository.existsByUsername(username)) {
            throw new DuplicateException("이미 사용중인 아이디입니다.");
        }
    }

    public void checkEmail(String email) {
        if(memberRepository.existsByEmail(email)) {
            throw new DuplicateException("이미 사용중인 이메일입니다.");
        }
    }

    public void checkPhoneNumber(String phoneNumber) {
        if(memberRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicateException("이미 사용중인 전화번호입니다.");
        }
    }

    public void checkNickname(String nickname) {
        if(memberRepository.existsByNickname(nickname)) {
            throw new DuplicateException("이미 사용중인 닉네임입니다.");
        }
    }

}
