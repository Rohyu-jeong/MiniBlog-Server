package com.rserver.miniblog.post.presentation.controller;

import com.rserver.miniblog.common.ApiResponse;
import com.rserver.miniblog.post.presentation.dto.response.LikeResponse;
import com.rserver.miniblog.post.application.service.LikeService;
import com.rserver.miniblog.auth.infrastructure.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ApiResponse<LikeResponse> toggleLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        boolean isLiked = likeService.toggleLike(memberDetails.getMember().getId(), postId);

        return ApiResponse.success(LikeResponse.of(isLiked));
    }

}
