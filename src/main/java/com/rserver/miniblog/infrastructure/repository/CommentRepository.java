package com.rserver.miniblog.infrastructure.repository;

import com.rserver.miniblog.domain.post.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
