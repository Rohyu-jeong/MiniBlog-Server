package com.rserver.miniblog.application.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String imageUrl;

    public static PostResponse of(Long id, String title, String content, String imageUrl) {
        return new PostResponse(id, title, content, imageUrl);
    }

}
