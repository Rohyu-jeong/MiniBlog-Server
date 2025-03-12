package com.rserver.miniblog.posttemp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTempRepository extends JpaRepository<PostTemp, Long> {
}
