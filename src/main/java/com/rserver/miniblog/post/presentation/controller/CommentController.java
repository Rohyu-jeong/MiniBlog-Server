package com.rserver.miniblog.post.presentation.controller;

import com.rserver.miniblog.post.presentation.dto.request.CommentRequest;
import com.rserver.miniblog.common.ApiResponse;
import com.rserver.miniblog.post.application.service.CommentService;
import com.rserver.miniblog.auth.infrastructure.security.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ApiResponse<Void> createComment(
            @Valid @RequestBody CommentRequest requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        commentService.create(memberDetails.getMember().getId(), requestDto);

        return ApiResponse.success();
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<Void> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        commentService.update(memberDetails.getMember().getId(), commentId, requestDto);

        return ApiResponse.success();
    }

}
