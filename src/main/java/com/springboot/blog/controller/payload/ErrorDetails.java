package com.springboot.blog.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/** This class is to map error details for exception handling */
@Getter
@Builder
public class ErrorDetails {

    private Date timestamp;

    private String message;

    private String errorDetails;
}
