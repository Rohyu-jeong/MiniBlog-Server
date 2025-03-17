package com.rserver.miniblog.infrastructure.repository;

import com.rserver.miniblog.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByNickname(String nickname);

    @Query("SELECT m.nickname FROM Member m WHERE m.username = :username")
    Optional<String> findNicknameByUsername(@Param("username") String username);

}
