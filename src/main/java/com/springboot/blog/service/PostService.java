package com.springboot.blog.service;

import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.controller.payload.PostResponse;

/** PostService is the interface includes services related to Posts */
public interface PostService {

    /**
     * service method to create new post
     * @param postDto
     * @return PostDto
     */
    PostDto createPost(PostDto postDto);

    /**
     * service method to get all posts. Supported with pagination and sorting
     * @param pageNo current page number. must not be negative (Optional)
     * @param pageSize posts per page. must not be negative (Optional)
     * @param sortBy sorting parameter (Optional)
     * @return PostResponse
     */
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);

    /**
     * service method to get post data by id
     * @param id post Id
     * @return PostDto
     */
    PostDto getPostById(Long id);

    /**
     * service method to update post data
     * @param dto updated post data
     * @param id post Id
     * @return updated post
     */
    PostDto updatePost(PostDto dto, Long id);

    /**
     * service method to delete post by Id
     * @param id post Id
     */
    void deletePostById(Long id);

}
