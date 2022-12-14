package com.springboot.blog.controller;

import com.springboot.blog.common.WsPath;
import com.springboot.blog.common.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** The CommentController is the rest api interface for COMMENT resources  */
@RestController
@AllArgsConstructor
@Tag(name = "REST APIs for COMMENT Resources")
public class CommentController {

    private CommentService commentService;

    /**
     * createComment method is to create comment for a given post
     * @param postId Id of the post
     * @param commentDto Comment request data
     * @return CommentDto
     */
    @Operation(summary = "REST End Point to Create New Comment")
    @PostMapping(WsPath.CREATE_COMMENT)
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    /**
     * getCommentsByPostId method is to get all comments for a given post
     * @param postId Id of the post
     * @return List of commentDto
     */
    @Operation(summary = "REST End Point to Get Comments by Post Id")
    @GetMapping(WsPath.COMMENTS)
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    /**
     * getCommentById method is to get a comment data of a given post
     * @param postId Id of the post
     * @param commentId Id of the comment
     * @return CommentDto
     */
    @Operation(summary = "REST End Point to Get a Comment Data by Comment Id")
    @GetMapping(WsPath.GET_COMMENT)
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    /**
     * updateComment method is to update comment of a given post
     * @param postId Id of the post
     * @param commentId Id of the comment
     * @param commentDto Request data of comment
     * @return CommentDto
     */
    @PutMapping(WsPath.UPDATE_COMMENT)
    @Operation(summary = "REST End Point to Update a Comment of Post")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    /**
     * deleteComment method is to delete comment of a given post
     * @param postId Id of the post
     * @param commentId Id of the comment
     * @return
     */
    @DeleteMapping(WsPath.DELETE_COMMENT)
    @Operation(summary = "REST End Point to Delete a Comment of a Post")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

}
