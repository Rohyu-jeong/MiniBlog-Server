package com.rserver.miniblog.posttemp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostTempResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String image;

    public static PostTempResponseDto of (Long id, String title, String content, String image) {
        return new PostTempResponseDto(id, title, content, image);
    }

}
