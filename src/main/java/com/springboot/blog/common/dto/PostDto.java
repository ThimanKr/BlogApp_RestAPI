package com.springboot.blog.common.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
/** The PostDto class is used as the dto for Post Entity */
public class PostDto {

    private Long id;
    @NotEmpty(message = "Title property should not be empty")
    @Size(min = 5, message = "Title should have at least 5 characters")
    private String title;
    @NotEmpty(message = "description property should not be empty")
    @Size(min = 10, message = "description should have at least 10 characters")
    private String description;
    @NotEmpty(message = "content property should not be empty")
    @Size(min = 100, message = "content should have at least 100 characters")
    private String content;
}
