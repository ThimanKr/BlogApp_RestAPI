package com.springboot.blog.service;

import com.springboot.blog.common.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto dto, Long id);

    void deletePostById(Long id);

}
