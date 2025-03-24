package com.rserver.miniblog.post.infrastructure.repository;

import com.rserver.miniblog.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
