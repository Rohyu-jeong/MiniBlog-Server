package com.rserver.miniblog.posttemp;

import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rserver.miniblog.post.domain.PostErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PostTempService {

    private final PostTempRepository postTempRepository;

    public Long create(Long memberId, PostTempRequestDto requestDto) {
        PostTemp post = PostTemp.createPostTemp(memberId, requestDto.getTitle(), requestDto.getContent(), requestDto.getImage());

        postTempRepository.save(post);

        return post.getId();
    }

    public Long update(Long memberId, Long postId, PostTempRequestDto requestDto) {
        PostTemp post = postTempRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(POST_NOT_FOUND));

        if (!post.getMemberId().equals(memberId)) {
            throw new UnauthorizedException(NOT_POST_OWNER);
        }

        post.updatePostTemp(requestDto.getTitle(), requestDto.getContent(), requestDto.getImage());

        return post.getId();
    }

    public PostTempResponseDto getPostById(Long postId) {
        PostTemp post = postTempRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(POST_NOT_FOUND));

        return PostTempResponseDto.of(post.getId(), post.getTitle(), post.getContent(), post.getImage());
    }

}
