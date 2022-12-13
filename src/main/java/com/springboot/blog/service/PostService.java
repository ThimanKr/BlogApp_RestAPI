package com.springboot.blog.service;

import com.springboot.blog.common.dto.PostDto;
import org.springframework.stereotype.Service;

public interface PostService {

    PostDto createPost(PostDto postDto);
}
