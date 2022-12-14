package com.springboot.blog.dao.repository;

import com.springboot.blog.dao.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** PostRepository is the repository for Post */
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
