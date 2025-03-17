package com.rserver.miniblog.application.service.post;

import com.rserver.miniblog.application.dto.request.CommentRequestDto;
import com.rserver.miniblog.domain.post.Comment;
import com.rserver.miniblog.exception.BadRequestException;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.exception.UnauthorizedException;
import com.rserver.miniblog.infrastructure.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public void create(Long memberId, CommentRequestDto requestDto) {
        Comment comment = Comment.createComment(memberId, requestDto.getPostId(), requestDto.getContent());

        commentRepository.save(comment);
    }

    public void update(Long memberId, Long CommentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(CommentId)
                .orElseThrow(() -> new NotFoundException("댓글을 찾을 수 없습니다."));

        if (!comment.getPostId().equals(requestDto.getPostId())) {
            throw new BadRequestException("해당 게시글의 댓글이 아닙니다.");
        }

        if (!comment.getMemberId().equals(memberId)) {
            throw new UnauthorizedException("본인의 댓글만 수정할 수 있습니다.");
        }

        comment.updateComment(requestDto.getContent());

    }

}
