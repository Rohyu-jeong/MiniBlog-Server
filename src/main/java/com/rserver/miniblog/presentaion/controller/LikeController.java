package com.rserver.miniblog.presentaion.controller;

import com.rserver.miniblog.application.service.post.LikeService;
import com.rserver.miniblog.infrastructure.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> toggleLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        boolean isLiked = likeService.toggleLike(memberDetails.getMember().getId(), postId);

        return ResponseEntity.ok(isLiked);
    }

}
