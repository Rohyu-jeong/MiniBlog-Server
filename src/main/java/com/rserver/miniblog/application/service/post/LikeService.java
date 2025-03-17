package com.rserver.miniblog.application.service.post;

import com.rserver.miniblog.domain.post.Like;
import com.rserver.miniblog.infrastructure.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;

    public boolean toggleLike(Long memberId, Long postId) {
        Optional<Like> existingLike = likeRepository.findByMemberIdAndPostId(memberId, postId);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return false;
        }

        Like like = Like.createLike(memberId, postId);
        likeRepository.save(like);

        return true;

    }

}
