package com.rserver.miniblog.application.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LikeResponse {

    private final boolean isLiked;

    public static LikeResponse of(boolean isLiked) {
        return new LikeResponse(isLiked);
    }

}
