package com.springboot.blog.controller.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
/** The payload class to map generate post request payload */
public class GeneratePostContentRequest {

    /** The property blog_title is the title provided by client to generate post */
    @NotEmpty(message = "blog_title property should not be empty")
    @Size(min = 10, message = "blog_title should have at least 10 characters")
    private String blog_title;

    /** The property blog_keywords is the keywords provided by the client to generate post */
    @NotEmpty(message = "name blog_keywords should not be empty")
    @Size(min = 10, message = "blog_keywords should have at least 10 characters")
    private String blog_keywords;
}
