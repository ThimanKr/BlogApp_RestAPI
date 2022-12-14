package com.springboot.blog.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
}
