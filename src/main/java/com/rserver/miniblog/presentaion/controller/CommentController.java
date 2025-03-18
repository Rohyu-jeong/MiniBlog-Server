package com.rserver.miniblog.presentaion.controller;

import com.rserver.miniblog.application.dto.request.CommentRequestDto;
import com.rserver.miniblog.application.dto.response.ApiResponse;
import com.rserver.miniblog.application.service.post.CommentService;
import com.rserver.miniblog.infrastructure.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@Validated
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ApiResponse<Void> createComment(
            @Validated @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        commentService.create(memberDetails.getMember().getId(), requestDto);

        return ApiResponse.success();
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<Void> updateComment(
            @PathVariable Long commentId,
            @Validated @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        commentService.update(memberDetails.getMember().getId(), commentId, requestDto);

        return ApiResponse.success();
    }

}
