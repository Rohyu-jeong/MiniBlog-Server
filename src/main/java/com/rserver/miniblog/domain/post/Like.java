package com.rserver.miniblog.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "likes", uniqueConstraints = @UniqueConstraint(
        columnNames = {"memberId", "postId"}
))
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long postId;

    private Like(Long memberId, Long postId) {
        this.memberId = memberId;
        this.postId = postId;
    }

    public static Like createLike(Long memberId, Long postId) {
        return new Like(memberId, postId);
    }

}
