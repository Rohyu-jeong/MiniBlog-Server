package com.rserver.miniblog.application.service.post;

import com.rserver.miniblog.application.dto.request.PostRequestDto;
import com.rserver.miniblog.application.dto.response.PostResponseDto;
import com.rserver.miniblog.domain.post.Post;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.exception.UnauthorizedException;
import com.rserver.miniblog.infrastructure.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;

    public Long create (Long memberId, PostRequestDto requestDto) {
        String imageUrl = (requestDto.getImage() != null && !requestDto.getImage().isEmpty())
                ? imageService.saveImage(requestDto.getImage())
                : null;

        Post post = Post.createPost(memberId, requestDto.getTitle(), requestDto.getContent(), imageUrl);

        postRepository.save(post);

        return post.getId();
    }

//    public Long update (Long memberId, Long postId, PostRequestDto requestDto, String imageUrl) {
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

    public PostResponseDto getPostById (Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다"));

        return PostResponseDto.of(post.getId(), post.getTitle(), post.getContent(), post.getImageUrl());
    }

}
