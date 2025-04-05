package com.rserver.miniblog.post.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

//    private final PostService postService;
//
//    @PostMapping
//    public ResponseEntity<Long> createPost(
//            @Validated @RequestBody PostRequestDto requestDto,
//            @AuthenticationPrincipal MemberDetails memberDetails
//    ) {
//        Long postId = postService.create(memberDetails.getMember().getId(), requestDto);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
//    }
//
//    @PatchMapping("/{postId}")
//    public ResponseEntity<Long> updatePost(
//            @PathVariable Long postId,
//            @Validated @RequestBody PostRequestDto requestDto,
//            @AuthenticationPrincipal MemberDetails memberDetails
//    ) {
//        Long updatedPostId = postService.update(memberDetails.getMember().getId(), postId, requestDto);
//
//        return ResponseEntity.ok(updatedPostId);
//    }

}
