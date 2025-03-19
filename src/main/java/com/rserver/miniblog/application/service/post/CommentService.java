package com.rserver.miniblog.application.service.post;

import com.rserver.miniblog.application.dto.request.CommentRequest;
import com.rserver.miniblog.domain.post.Comment;
import com.rserver.miniblog.exception.BadRequestException;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.exception.UnauthorizedException;
import com.rserver.miniblog.infrastructure.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rserver.miniblog.domain.post.PostErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public void create(Long memberId, CommentRequest requestDto) {
        Comment comment = Comment.createComment(memberId, requestDto.getPostId(), requestDto.getContent());

        commentRepository.save(comment);
    }

    public void update(Long memberId, Long CommentId, CommentRequest requestDto) {
        Comment comment = commentRepository.findById(CommentId)
                .orElseThrow(() -> new NotFoundException(COMMENT_NOT_FOUND));

        if (!comment.getPostId().equals(requestDto.getPostId())) {
            throw new BadRequestException(INVALID_COMMENT);
        }

        if (!comment.getMemberId().equals(memberId)) {
            throw new UnauthorizedException(NOT_COMMENT_OWNER);
        }

        comment.updateComment(requestDto.getContent());

    }

}
