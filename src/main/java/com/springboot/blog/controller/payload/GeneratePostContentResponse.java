package com.springboot.blog.controller.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/** The payload class to map generate post response payload */
public class GeneratePostContentResponse {

    /** The property status is the status of generate post content response */
    private String status;

    /** The property content is the generated content of the post content response */
    private String content;
}
