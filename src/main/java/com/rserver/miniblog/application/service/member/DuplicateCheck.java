package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.application.dto.request.SignUpRequestDto;
import com.rserver.miniblog.exception.DuplicateException;
import com.rserver.miniblog.infrastructure.repository.MemberRepository;
import com.rserver.miniblog.infrastructure.repository.NicknameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DuplicateCheck {

    private final MemberRepository memberRepository;
    private final NicknameRepository nicknameRepository;

    public void validateForRegistration(SignUpRequestDto requestDto) throws DuplicateException {
        checkUsername(requestDto.getUsername());
        checkEmail(requestDto.getEmail());
        checkPhoneNumber(requestDto.getPhoneNumber());
    }

    public void checkUsername(String username) {
        if(memberRepository.existsByUsername(username)) {
            log.warn("중복된 아이디 발견: {}", username);
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
        if(nicknameRepository.existsByNickname(nickname)) {
            throw new DuplicateException("이미 사용중인 닉네임입니다.");
        }
    }

}
