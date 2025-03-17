package com.rserver.miniblog.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    private Comment(Long memberId, Long postId, String content) {
        this.memberId = memberId;
        this.postId = postId;
        this.content = content;
    }

    public static Comment createComment(Long memberId, Long postId, String content) {
        return new Comment(memberId, postId, content);
    }

    public void updateComment(String newContent) {
        this.content = newContent;
    }

}
