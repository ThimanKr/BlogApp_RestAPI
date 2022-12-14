package com.springboot.blog.service;

import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.dao.entity.PostEntity;
import com.springboot.blog.dao.repository.PostRepository;
import com.springboot.blog.service.impl.PostServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

/** This class is for test cases related to Post Service */
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private PostDto postDto;

    private PostEntity postEntity;

    /**
     * This method is to set up initial objects required for tests
     */
    @BeforeEach
    public void setup(){
        postDto = PostDto.builder()
                .title("New Post")
                .description("New Post description")
                .content("New Post Content")
                .build();

        postEntity = new PostEntity();
        postEntity.setTitle("New Post");
        postEntity.setDescription("New Post description");
        postEntity.setContent("New Post Content");

    }

    /**
     * Test method to test createPost of service layer
     */
    @Test
    public void givenPost_whenCreatePost_thenReturnPostDto(){

        // mock save method
        when(postRepository.save(Mockito.any(PostEntity.class))).thenReturn(postEntity);

        PostDto dto = postService.createPost(postDto);

        // test returned value
        Assertions.assertThat(dto).isNotNull();

    }

    /**
     * Test method to test getPostById of service layer
     */
    @Test
    public void givenPost_whenGetPostById_thenReturnPostDto(){

        // mock findById method
        when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(postEntity));

        PostDto dto = postService.getPostById(1L);

        // test returned value
        Assertions.assertThat(dto).isNotNull();

    }

    /**
     * Test method to test updatePost of service layer
     */
    @Test
    public void givenPost_whenUpdatePost_thenReturnPostDto(){

        when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(postEntity));
        when(postRepository.save(Mockito.any(PostEntity.class))).thenReturn(postEntity);

        PostDto dto = postService.updatePost(postDto, 1L);

        Assertions.assertThat(dto).isNotNull();

    }

    /**
     * Test method to test deletePostById of service layer
     */
    @Test
    public void givenPost_whenDeletePost_thenReturnDeletedPost(){

        when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(postEntity));
        org.junit.jupiter.api.Assertions.assertAll(() -> postService.deletePostById(1L));

    }
}
