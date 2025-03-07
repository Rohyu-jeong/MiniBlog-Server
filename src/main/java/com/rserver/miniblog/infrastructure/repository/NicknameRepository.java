package com.rserver.miniblog.infrastructure.repository;

import com.rserver.miniblog.domain.member.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NicknameRepository extends JpaRepository<Nickname, Long> {

    Optional<Nickname> findByMemberId (Long memberId);

    boolean existsByNickname (String nickname);

}
