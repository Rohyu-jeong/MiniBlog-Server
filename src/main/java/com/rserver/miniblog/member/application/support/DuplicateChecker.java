package com.rserver.miniblog.member.application.support;

import com.rserver.miniblog.member.presentation.dto.request.SignUpRequest;
import com.rserver.miniblog.exception.DuplicateException;
import com.rserver.miniblog.member.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.rserver.miniblog.member.domain.MemberErrorMessage.*;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DuplicateChecker {

    private final MemberRepository memberRepository;

    public void validateForRegistration(SignUpRequest requestDto) throws DuplicateException {
        checkUsername(requestDto.getUsername());
        checkNickname(requestDto.getNickname());
        checkEmail(requestDto.getEmail());
        checkPhoneNumber(requestDto.getPhoneNumber());
    }

    public void checkUsername(String username) {
        if(memberRepository.existsByUsername(username)) {
            throw new DuplicateException(USERNAME_DUPLICATE);
        }
    }

    public void checkEmail(String email) {
        if(memberRepository.existsByEmail(email)) {
            throw new DuplicateException(EMAIL_DUPLICATE);
        }
    }

    public void checkPhoneNumber(String phoneNumber) {
        if(memberRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicateException(PHONE_DUPLICATE);
        }
    }

    public void checkNickname(String nickname) {
        if(memberRepository.existsByNickname(nickname)) {
            throw new DuplicateException(NICKNAME_DUPLICATE);
        }
    }

}
