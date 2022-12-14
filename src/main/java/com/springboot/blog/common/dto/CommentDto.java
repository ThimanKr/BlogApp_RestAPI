package com.springboot.blog.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CommentDto {

    private Long id;
    private String name;
    private String email;
    private String body;
}
