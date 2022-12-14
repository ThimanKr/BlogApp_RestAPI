package com.springboot.blog.service;

import com.springboot.blog.common.dto.CommentDto;

import java.util.List;

/** CommentService is the interface includes services related to Comments */
public interface CommentService {

    /**
     * service method to create comment for the given post
     * @param postId Id of the post
     * @param commentDto commentDto
     * @return commentDto
     */
    CommentDto createComment(Long postId, CommentDto commentDto);

    /**
     * service method to get all comments of the given post
     * @param postId Id of the post
     * @return List of commentDtos
     */
    List<CommentDto> getCommentsByPostId(Long postId);

    /**
     * service method to get comment data of the given post
     * @param postId Id of the post
     * @param commentId Id of the comment
     * @return commentDto
     */
    CommentDto getCommentById(Long postId, Long commentId);

    /**
     * service method to update comment of the given post
     * @param postId Id of the post
     * @param commentId Id of the comment
     * @param commentRequest request comment data
     * @return commentDto
     */
    CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

    /**
     * service method to delete comment by id of the given post
     * @param postId Id of the post
     * @param commentId Id of the comment
     */
    void deleteComment(Long postId, Long commentId);
}
