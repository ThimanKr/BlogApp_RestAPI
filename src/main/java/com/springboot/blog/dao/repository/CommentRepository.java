package com.springboot.blog.dao.repository;

import com.springboot.blog.dao.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** CommentRepository is the repository for Comment */
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    /** method to get List of comments by post id */
    List<CommentEntity> findByPostId(Long postId);
}
