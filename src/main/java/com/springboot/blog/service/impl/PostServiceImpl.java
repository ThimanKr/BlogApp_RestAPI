package com.springboot.blog.service.impl;

import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.dao.entity.PostEntity;
import com.springboot.blog.dao.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert dto to entity
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postEntity.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());

        PostEntity savedPost = postRepository.save(postEntity);

        // Convert saved post to dto for response
        return PostDto.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .description(savedPost.getDescription())
                .content(savedPost.getContent())
                .build();

    }
}
