package com.rserver.miniblog.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(columnDefinition = "LONGTEXT")
    private String imageUrl;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    private Post(Long memberId, String title, String content, String imageUrl) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public static Post createPost(Long memberId, String title, String content, String imageUrl) {
        return new Post(memberId, title, content, imageUrl);
    }

    public void updatePost (String newTitle, String newContent, String newImageUrl) {
        this.title = newTitle;
        this.content = newContent;
        this.imageUrl = newImageUrl;
    }

}
