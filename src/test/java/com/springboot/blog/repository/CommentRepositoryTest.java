package com.springboot.blog.repository;

import com.springboot.blog.dao.entity.CommentEntity;
import com.springboot.blog.dao.entity.PostEntity;
import com.springboot.blog.dao.repository.CommentRepository;
import com.springboot.blog.dao.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/** This class is for test cases related to Comment Repository */
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    private PostEntity savedPost;

    @BeforeEach
    public void createPost(){
        PostEntity post = new PostEntity();
        post.setTitle("New Post");
        post.setDescription("New Post description");
        post.setContent("New Post Content");
        savedPost = postRepository.save(post);

    }

    /**
     * Test method for save comment
     */
    @Test
    @DisplayName("JUnit test for save comment")
    public void givenComment_whenSave_thenReturnSavedComment(){

        // given post and comment
        CommentEntity comment = new CommentEntity();
        comment.setName("Jack");
        comment.setEmail("jk@gmail.com");
        comment.setBody("Excellent post");
        comment.setPost(savedPost);

        // when save comment
        CommentEntity savedComment = commentRepository.save(comment);

        // then return comment
        Assertions.assertThat(savedComment.getId()).isNotNull();
    }

    /**
     * Test method for findAll comments of a given post
     */
    @Test
    @DisplayName("JUnit test for get comment data by comment id")
    public void givenCommentList_whenFindAllCommentsByPostId_thenReturnCommentList(){

        // given comment list
        CommentEntity entity1 = new CommentEntity();
        entity1.setName("Jack");
        entity1.setEmail("jack123@gmail.com");
        entity1.setBody("Nice post");
        entity1.setPost(savedPost);
        commentRepository.save(entity1);

        CommentEntity entity2 = new CommentEntity();
        entity2.setName("Maya");
        entity2.setEmail("mj@gmail.com");
        entity2.setBody("Excellent post");
        entity2.setPost(savedPost);
        commentRepository.save(entity2);

        // when findAll comments of post by post
        List<CommentEntity> comments = commentRepository.findByPostId(savedPost.getId());

        // then return comment list
        Assertions.assertThat(comments.size()).isEqualTo(2);

    }

    /**
     * Test method for deleteById
     */
    @Test
    @DisplayName("JUnit test for delete comment of a post by comment id")
    public void givenComment_thenDeleteComment_thenReturnDeletedComment(){

        // given comment
        CommentEntity comment = new CommentEntity();
        comment.setName("Jack");
        comment.setEmail("jk@gmail.com");
        comment.setBody("Excellent post");
        comment.setPost(savedPost);

        CommentEntity savedComment = commentRepository.save(comment);

        // when delete comment
        commentRepository.deleteById(comment.getId());
        Optional<CommentEntity> deletedComment = commentRepository.findById(savedComment.getId());

        // then return deleted comment
        Assertions.assertThat(deletedComment).isEmpty();


    }

}