package com.rserver.miniblog.application.service.member;

import com.rserver.miniblog.domain.member.Nickname;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.infrastructure.repository.NicknameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NicknameService {

    private final NicknameRepository nicknameRepository;

    public void save (Long memberId, String nickname) {
        Nickname createNickname = Nickname.createNickname(memberId, nickname);

        nicknameRepository.save(createNickname);
    }

    public Nickname find (Long memberId) {
        return nicknameRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException("닉네임을 찾을 수 없습니다."));
    }

}
