package com.springboot.blog.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
/** The PostDto class is used as the dto for Post Entity */
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
}
