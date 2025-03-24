package com.rserver.miniblog.posttemp;

import com.rserver.miniblog.post.presentation.dto.response.PostResponse;
import com.rserver.miniblog.post.application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/public/posts")
@Validated
@RequiredArgsConstructor
public class PublicPostController {

    private final PostService postService;
    private final PostTempService postTempService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Long> createPost(
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        Long postId = postService.create(1L, title, content, image);

        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost (@PathVariable Long postId) {
        PostResponse post = postService.getPostById(postId);

        return ResponseEntity.ok(post);
    }

    @PostMapping("/temp")
    public ResponseEntity<Long> createPostTemp(@Validated @RequestBody PostTempRequestDto requestDto) {
        Long postId = postTempService.create(1L, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }

    @GetMapping("/temp/{postId}")
    public ResponseEntity<PostTempResponseDto> getPostTemp (@PathVariable Long postId) {
        PostTempResponseDto post = postTempService.getPostById(postId);

        return ResponseEntity.ok(post);
    }

}
