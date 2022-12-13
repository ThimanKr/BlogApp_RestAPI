package com.springboot.blog.service.impl;

import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.dao.entity.PostEntity;
import com.springboot.blog.dao.repository.PostRepository;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert dto to entity
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
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

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(postEntity ->
            PostDto.builder()
                    .id(postEntity.getId())
                    .title(postEntity.getTitle())
                    .description(postEntity.getDescription())
                    .content(postEntity.getContent())
                    .build()
        ).toList();
        return postDtos;
    }

    @Override
    public PostDto getPostById(Long id) {

        Optional<PostEntity> postEntity = postRepository.findById(id);
        if(postEntity.isPresent()){
            PostEntity post = postEntity.get();
           return PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .content(post.getContent())
                    .build();

        } else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }

    @Override
    public PostDto updatePost(PostDto dto, Long id) {

        Optional<PostEntity> postEntity = postRepository.findById(id);
        if(postEntity.isPresent()){
            PostEntity post = postEntity.get();
            post.setTitle(dto.getTitle());
            post.setDescription(dto.getDescription());
            post.setContent(dto.getContent());

            PostEntity updatedPost = postRepository.save(post);
            return PostDto.builder()
                    .id(updatedPost.getId())
                    .title(updatedPost.getTitle())
                    .description(updatedPost.getDescription())
                    .content(updatedPost.getContent())
                    .build();

        } else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }

    @Override
    public void deletePostById(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }
}
