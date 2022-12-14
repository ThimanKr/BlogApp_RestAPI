package com.springboot.blog.controller.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
/** The payload class to map generate post title request payload */
public class GeneratePostTitleRequest {

    /** The property blog_intro is the intro provided by client to generate post title*/
    @NotEmpty(message = "blog_intro property should not be empty")
    @Size(min = 10, message = "blog_intro should have at least 10 characters")
    private String blog_intro;

    /** The property blog_keywords is the keywords provided by the client to generate post */
    @NotEmpty(message = "name blog_keywords should not be empty")
    @Size(min = 10, message = "blog_keywords should have at least 10 characters")
    private String blog_keywords;
}

