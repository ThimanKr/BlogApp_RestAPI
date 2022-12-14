package com.springboot.blog.service.impl;

import com.springboot.blog.common.dto.CommentDto;
import com.springboot.blog.dao.entity.CommentEntity;
import com.springboot.blog.dao.entity.PostEntity;
import com.springboot.blog.dao.repository.CommentRepository;
import com.springboot.blog.dao.repository.PostRepository;
import com.springboot.blog.exception.APIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.helper.CommentServiceHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private CommentServiceHelper serviceHelper;


    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        CommentEntity comment = serviceHelper.convertDtoToCommentEntity(commentDto);

        // retrieve post entity by id
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);

        // save comment entity
        CommentEntity newComment =  commentRepository.save(comment);

        CommentDto dto = serviceHelper.convertCommentEntityToDto(newComment);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // retrieve comments by postId
        List<CommentEntity> comments = commentRepository.findByPostId(postId);

        // convert list of comment entities to list of comment dto's
        return comments.stream().map(comment -> serviceHelper.convertCommentEntityToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return serviceHelper.convertCommentEntityToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        CommentEntity updatedComment = commentRepository.save(comment);
        return serviceHelper.convertCommentEntityToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
// retrieve post entity by id
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        commentRepository.delete(comment);
    }
}
