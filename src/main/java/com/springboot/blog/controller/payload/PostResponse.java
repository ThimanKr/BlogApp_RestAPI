package com.springboot.blog.controller.payload;

import com.springboot.blog.common.dto.PostDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
/** The payload class to map get all posts response with pagination payload */
public class PostResponse {

    /** The property posts is the list of posts of get all posts pagination response */
    private List<PostDto> posts;

    /** The property pageNo is the current page number of get all posts pagination response */
    private int pageNo;

    /** The property pageSize is the current page size of get all posts pagination response */
    private int pageSize;

    /** The property totalElements is the total posts of get all posts pagination response */
    private Long totalElements;

    /** The property totalPage is the total pages of get all posts pagination response */
    private int totalPage;

}
