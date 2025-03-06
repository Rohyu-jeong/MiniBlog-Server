package com.rserver.miniblog.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Nickname {

    @Id
    @GeneratedValue
    @Column(name = "nickname_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Nickname (Long memberId, String nickname) {
        this.memberId = memberId;
        this.nickname = nickname;
    }

    public static Nickname createNickname (Long memberId, String nickname) {
        return new Nickname(memberId, nickname);
    }

    public void updateNickname (String newNickname) {
        this.nickname = newNickname;
    }

}
