package com.rserver.miniblog.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(unique = true, nullable = false, length = 13)
    private String phoneNumber;

    @Column(unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private Member(String username, String encodePassword, String email, String phoneNumber) {
        this.username = username;
        this.password = encodePassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.type = LoginType.APP;
        this.role = Role.ROLE_USER;
    }

    public static Member createMember(String username, String password, String email, String phoneNumber) {
        return new Member(username.toLowerCase(), password, email, phoneNumber);
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

}
