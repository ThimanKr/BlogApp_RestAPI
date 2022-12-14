package com.springboot.blog.service.impl;

import com.springboot.blog.common.constants.PostConstants;
import com.springboot.blog.common.dto.GeneratePostContentDto;
import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.controller.payload.PostResponse;
import com.springboot.blog.controller.payload.TextCortexRequest;
import com.springboot.blog.controller.payload.TextCortexResponse;
import com.springboot.blog.dao.entity.PostEntity;
import com.springboot.blog.dao.repository.PostRepository;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.helper.PostServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostServiceHelper serviceHelper;

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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {

        // Create Pageable instance for pagination
        // Default sorting is Ascending
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        // pass pageable data to in-built findAll method
        Page<PostEntity> posts = postRepository.findAll(pageable);

        // get content from page object
        List<PostEntity> postEntityList = posts.getContent();

        // convert post entity list to postDto
        List<PostDto> postDtos = postEntityList.stream().map(postEntity ->
            PostDto.builder()
                    .id(postEntity.getId())
                    .title(postEntity.getTitle())
                    .description(postEntity.getDescription())
                    .content(postEntity.getContent())
                    .build()
        ).toList();

        return PostResponse.builder()
                .posts(postDtos)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPage(posts.getTotalPages())
                .build();

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

    @Override
    public TextCortexResponse generatePostContent(GeneratePostContentDto dto) {
        // Create a RestTemplate for new request to 3rd party API
        RestTemplate template = new RestTemplate();
        // Create request
        TextCortexRequest request = serviceHelper.createTextCortexRequest(dto);

        // Get the response from
        TextCortexResponse apiResponse = template.postForObject(PostConstants.TEXT_CORTEX_URL, request, TextCortexResponse.class);
        return apiResponse;
    }
}
