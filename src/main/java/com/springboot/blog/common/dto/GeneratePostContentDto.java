package com.springboot.blog.common.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
/** The GeneratePostContentDto class is used as the dto for generate post content */
public class GeneratePostContentDto {

    /** The property blog_title is the title provided by client to generate post */

    private String blog_title;

    /** The property blog_keywords is the keywords provided by the client to generate post */
    private String blog_keywords;
}
