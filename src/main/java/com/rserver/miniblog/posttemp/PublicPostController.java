package com.rserver.miniblog.posttemp;

import com.rserver.miniblog.application.dto.request.PostRequestDto;
import com.rserver.miniblog.application.dto.response.PostResponseDto;
import com.rserver.miniblog.application.service.post.ImageService;
import com.rserver.miniblog.application.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/posts")
@Validated
@RequiredArgsConstructor
public class PublicPostController {

    private final PostService postService;
    private final ImageService imageService;
    private final PostTempService postTempService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Long> createPost(@ModelAttribute PostRequestDto requestDto) {
        Long postId = postService.create(1L, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost (@PathVariable Long postId) {
        PostResponseDto post = postService.getPostById(postId);

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
