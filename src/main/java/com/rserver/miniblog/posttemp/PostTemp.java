package com.rserver.miniblog.posttemp;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostTemp {

    @Id
    @GeneratedValue
    @Column(name = "post_temp_id")
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    private PostTemp(Long memberId, String title, String content, String image) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public static PostTemp createPostTemp(Long memberId, String title, String content, String image) {
        return new PostTemp(memberId, title, content, image);
    }

    public void updatePostTemp(String newTitle, String newContent, String newImage) {
        this.title = newTitle;
        this.content = newContent;
        this.image = newImage;
    }

}
