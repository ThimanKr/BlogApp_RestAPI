package com.springboot.blog.dao.repository;

import com.springboot.blog.dao.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
