package com.springboot.blog.controller.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/** The payload class to map generate post request payload */
public class GeneratePostContentRequest {

    /** The property blog_title is the title provided by client to generate post */
    private String blog_title;

    /** The property blog_keywords is the keywords provided by the client to generate post */
    private String blog_keywords;
}
