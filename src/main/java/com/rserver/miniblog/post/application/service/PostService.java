package com.rserver.miniblog.post.application.service;

import com.rserver.miniblog.post.presentation.dto.response.PostResponse;
import com.rserver.miniblog.post.domain.Post;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.post.infrastructure.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;

    public Long create(Long memberId, String title, String content, MultipartFile image) {
        String imageUrl = (image != null && !image.isEmpty())
                ? imageService.saveImage(image)
                : null;

        Post post = Post.createPost(memberId, title, content, imageUrl);

        postRepository.save(post);

        return post.getId();
    }

//    public Long update(Long memberId, Long postId, PostRequestDto requestDto, String imageUrl) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));
//
//        if (!post.getMemberId().equals(memberId)) {
//            throw new UnauthorizedException("본인이 작성한 게시글만 수정할 수 있습니다.");
//        }
//
//        post.updatePost(requestDto.getTitle(), requestDto.getContent(), imageUrl);
//
//        return post.getId();
//    }

    public PostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다"));

        return PostResponse.of(post.getId(), post.getTitle(), post.getContent(), post.getImageUrl());
    }

}
