package com.springboot.blog.repository;

import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.dao.entity.PostEntity;
import com.springboot.blog.dao.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


/** This class is for test cases related to Post Repository */
@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    private PostEntity post = new PostEntity();

    /**
     * Create Post before each test method run
     */
    @BeforeEach
    public void createPost(){
        post.setTitle("New Post");
        post.setDescription("New Post description");
        post.setContent("New Post Content");
    }

    /**
     * Test method for save post
     */
    @Test
    @DisplayName("JUnit test for save post")
    public void givenPost_whenSave_thenReturnSavedPost(){

        // given post
        PostEntity postEntity = post;

        // when save post
        PostEntity savedPost = postRepository.save(postEntity);

        // then verify returned post
        Assertions.assertThat(savedPost).isNotNull();
        Assertions.assertThat(savedPost.getId()).isGreaterThan(0);
    }

    /**
     * Test method for findById
     */
    @Test
    @DisplayName("JUnit test for get post by Id")
    public void givenPost_whenFindByPostId_thenReturnPost(){

        // given post
        PostEntity postEntity = post;
        PostEntity savedPost = postRepository.save(postEntity);

        // when find post by if
        PostEntity entity = postRepository.findById(postEntity.getId()).get();

        // then return same post
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getId()).isEqualTo(savedPost.getId());
    }

    /**
     * Test method for findAll posts
     */
    @Test
    @DisplayName("JUnit test for get all posts")
    public void givenPostList_whenFindAll_thenReturnPostList(){

        // given Post List
        PostEntity post1 = new PostEntity();
        post1.setTitle("Post 2");
        post1.setDescription("Post 2 description");
        post1.setContent("POst 2 content");

        postRepository.save(post);
        postRepository.save(post1);

        // when findAll posts
        List<PostEntity> posts = postRepository.findAll();

        // then return same list
        Assertions.assertThat(posts.size()).isEqualTo(2);

    }

    /**
     * Test method for update posts
     */
    @Test
    @DisplayName("JUnit test for update post")
    public void givenPost_whenUpdatePost_thenReturnUpdatedPost(){

        // given Post List
        PostEntity savedPost = postRepository.save(post);

        // when update post data
        PostEntity postEntity = postRepository.findById(savedPost.getId()).get();
        postEntity.setTitle("Update Post title");
        PostEntity updatedPost = postRepository.save(postEntity);

        // then return same list
        Assertions.assertThat(updatedPost.getTitle()).isEqualTo("Update Post title");

    }

    /**
     * Test method for delete post
     */
    @Test
    @DisplayName("JUnit test for delete post")
    public void givenPost_whenDeletePost_thenReturnDeletedPost(){

        // given Post List
        PostEntity savedPost = postRepository.save(post);

        // when delete post
        postRepository.deleteById(savedPost.getId());

        Optional<PostEntity> deletedPost = postRepository.findById(savedPost.getId());

        // then return deleted post
        Assertions.assertThat(deletedPost).isEmpty();

    }


}