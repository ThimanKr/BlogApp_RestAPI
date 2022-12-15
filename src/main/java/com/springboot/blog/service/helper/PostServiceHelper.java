package com.springboot.blog.service.helper;

import com.springboot.blog.common.constants.PostConstants;
import com.springboot.blog.common.dto.GeneratePostContentDto;
import com.springboot.blog.common.dto.GeneratePostTitleDto;
import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.controller.payload.TextCortexCreateBlogRequest;
import com.springboot.blog.controller.payload.TextCortexCreateBlogTitleRequest;
import com.springboot.blog.dao.entity.PostEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** PostServiceHelper is the helper class for post service */
@Component
public class PostServiceHelper {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * This method is to create request body for createTextCortexGeneratePostRequest
     * @param dto GeneratePostContentDto
     * @return TextCortexCreateBlogRequest
     */
    public TextCortexCreateBlogRequest createTextCortexGeneratePostRequest(GeneratePostContentDto dto){

        // create TextCortexCreateBlogRequest for generate blog text
        return TextCortexCreateBlogRequest.builder()
                .template_name("blog_body")
                .prompt(dto)
                .token_count(100)
                .temperature(0.65)
                .n_gen(2)
                .source_language("en")
                .api_key(PostConstants.TEXT_CORTEX_API_KEY)
                .build();

    }

    /**
     * This method is to create request body for createTextCortexGenerateTitleRequest
     * @param dto GeneratePostTitleDto
     * @return TextCortexCreateBlogTitleRequest
     */
    public TextCortexCreateBlogTitleRequest createTextCortexGenerateTitleRequest(GeneratePostTitleDto dto){

        // create TextCortexCreateBlogRequest for generate blog title
        return TextCortexCreateBlogTitleRequest.builder()
                .template_name("blog_title")
                .prompt(dto)
                .token_count(100)
                .temperature(0.65)
                .n_gen(2)
                .source_language("en")
                .api_key(PostConstants.TEXT_CORTEX_API_KEY)
                .build();

    }

    /** Method to convert PostEntity to PostDto */
    public PostDto convertPostEntityToPostDto(PostEntity entity){
        return PostDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .content(entity.getContent())
                .build();

    }

    /** Method to convert PostDto to PostEntity */
    public PostEntity convertPostDtoToPostEntity(PostDto dto){

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(dto.getTitle());
        postEntity.setDescription(dto.getDescription());
        postEntity.setContent(dto.getContent());
        return postEntity;

    }

}
