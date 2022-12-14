package com.springboot.blog.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/** The GeneratePostTitleDto class is used as the dto for generate post content */
public class GeneratePostTitleDto {

    /** The property blog_intro is the intro provided by client to generate post title*/
    private String blog_intro;

    /** The property blog_keywords is the keywords provided by the client to generate post */
    private String blog_keywords;
}
