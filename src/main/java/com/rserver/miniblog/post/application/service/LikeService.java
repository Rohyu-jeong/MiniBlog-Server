package com.rserver.miniblog.post.application.service;

import com.rserver.miniblog.post.domain.Like;
import com.rserver.miniblog.post.infrastructure.repository.LikeRepository;
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
