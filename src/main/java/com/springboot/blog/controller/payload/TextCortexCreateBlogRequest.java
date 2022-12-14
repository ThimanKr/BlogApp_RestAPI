package com.springboot.blog.controller.payload;

import com.springboot.blog.common.dto.GeneratePostContentDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
/** This payload class is to generate request for TextCortexCreateBlog request */
public class TextCortexCreateBlogRequest {

    private String template_name;

    private double temperature;

    private int token_count;

    private int n_gen;

    private String source_language;

    private String api_key;

    private GeneratePostContentDto prompt;


}
