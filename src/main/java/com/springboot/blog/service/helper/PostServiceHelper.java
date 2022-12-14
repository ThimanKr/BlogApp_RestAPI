package com.springboot.blog.service.helper;

import com.springboot.blog.common.constants.PostConstants;
import com.springboot.blog.common.dto.GeneratePostContentDto;
import com.springboot.blog.controller.payload.TextCortexRequest;
import com.springboot.blog.controller.payload.TextCortexResponse;
import org.springframework.stereotype.Component;

/** PostServiceHelper is the helper class for post service */
@Component
public class PostServiceHelper {

    public TextCortexRequest createTextCortexRequest(GeneratePostContentDto dto){

        // create TextCortexRequest for generate blog text
        return TextCortexRequest.builder()
                .template_name("blog_body")
                .prompt(dto)
                .token_count(100)
                .temperature(0.65)
                .n_gen(2)
                .source_language("en")
                .api_key(PostConstants.TEXT_CORTEX_API_KEY)
                .build();

    }


}
