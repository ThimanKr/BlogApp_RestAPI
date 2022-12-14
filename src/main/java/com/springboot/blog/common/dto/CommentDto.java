package com.springboot.blog.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/** The CommentDto class is used as the dto for Comment Entity */
@Data
public class CommentDto {

    private Long id;
    @NotEmpty(message = "name property should not be empty")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @NotEmpty(message = "email property should not be empty")
    @Email(message = "please provide valid email")
    private String email;
    @NotEmpty(message = "body property should not be empty")
    @Size(min = 5, message = "Body should have at least 5 characters")
    private String body;
}
