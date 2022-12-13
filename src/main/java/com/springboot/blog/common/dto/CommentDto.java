package com.springboot.blog.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDto {

    private Long id;
    private String name;
    private String email;
    private String body;
}
