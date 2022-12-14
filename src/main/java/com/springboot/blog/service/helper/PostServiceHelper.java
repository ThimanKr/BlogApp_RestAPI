package com.springboot.blog.service.helper;

import com.springboot.blog.common.constants.PostConstants;
import com.springboot.blog.common.dto.GeneratePostContentDto;
import com.springboot.blog.common.dto.GeneratePostTitleDto;
import com.springboot.blog.controller.payload.TextCortexCreateBlogRequest;
import com.springboot.blog.controller.payload.TextCortexCreateBlogTitleRequest;
import org.springframework.stereotype.Component;

/** PostServiceHelper is the helper class for post service */
@Component
public class PostServiceHelper {

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


}
