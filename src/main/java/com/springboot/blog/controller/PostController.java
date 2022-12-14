package com.springboot.blog.controller;

import com.springboot.blog.common.WsPath;
import com.springboot.blog.common.constants.PostConstants;
import com.springboot.blog.common.dto.GeneratePostContentDto;
import com.springboot.blog.common.dto.GeneratePostTitleDto;
import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.controller.helper.PostControllerHelper;
import com.springboot.blog.controller.payload.*;
import com.springboot.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** The PostController is the rest api interface for POST resources  */
@Tag(name = "REST APIs for POST Resources")
@RestController
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private PostControllerHelper controllerHelper;


    /**
     * createPost method is to create new post
     * @param post request data for create new post
     * @return post
     */
    @Operation(summary = "CREATE POST - REST End Point to Create New Post")
    @PostMapping(WsPath.CREATE_POST)
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto post){

        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);

    }

    /**
     * getPostById method is to get post data by post id
     * @param id post Id
     * @return post
     */
    @Operation(summary = "GET POST - REST End Point to Get a Post by Post Id")
    @GetMapping(WsPath.GET_POST)
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){

        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    /**
     * updatePost is the method to update post data by post id
     * @param postDto updated post request data
     * @param id post Id
     * @return updated post entity
     */
    @Operation(summary = "UPDATE POST - REST End Point to Update a Post by Post Id")
    @PutMapping(WsPath.UPDATE_POST)
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){

        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    /**
     * deletePost is the method to delete a post by post id
     * @param id post Id
     * @return ResponseEntity
     */
    @Operation(summary = "DELETE POST - REST End Point to Delete a Post by Post Id")
    @DeleteMapping(WsPath.DELETE_POST)
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        postService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

    /**
     * getAllPosts is the method to get all the posts with pagination and sorting support
     * @param pageNo current page number. must not be negative (Optional)
     * @param pageSize posts per page. must not be negative (Optional)
     * @param sortBy sorting parameter (Optional)
     * @return PostResponse
     */
    @Operation(summary = "GET ALL POSTS - REST End Point to Get All Posts with pagination and sorting")
    @GetMapping(WsPath.POSTS)
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = PostConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PostConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PostConstants.DEFAULT_SORT_BY, required = false) String sortBy){

        return postService.getAllPosts(pageNo, pageSize, sortBy);
    }

    /**
     * generatePostTitle is the method to generate title for given post intro and keywords using 3rd party API. (https://documenter.getpostman.com/view/936254/2s83tCLYi9#106c9e5d-0b6c-4017-8e9f-605ef8d04385)
     * @param request GeneratePostTitleRequest
     * @return GeneratePostContentResponse
     */
    @Operation(summary = "GENERATE POST TITLE- REST End Point to Generate a Post Title based on given blog intro and keywords")
    @PostMapping(WsPath.GENERATE_POST_TITLE)
    public GeneratePostContentResponse generatePostTitle(@Valid @RequestBody GeneratePostTitleRequest request){

        GeneratePostTitleDto dto = controllerHelper.convertRequestToGenerateTitleDto(request);
        TextCortexResponse apiResponse = postService.generatePostTitle(dto);
        return controllerHelper.convertTextCortexResponseToPostContentResponse(apiResponse);

    }

    /**
     * generatePostText is the method to generate text for given title and keywords using 3rd party API. (https://documenter.getpostman.com/view/936254/2s83tCLYi9#24be6895-850f-433b-80fd-312ac1acffad)
     * @param request object of postTitle and postKeywords
     * @return GeneratePostContentResponse with generated text and success status
     */
    @Operation(summary = "GENERATE POST - REST End Point to Generate a Post content based on given keywords and a blog title")
    @PostMapping(WsPath.GENERATE_POST)
    public GeneratePostContentResponse generatePostText(@Valid @RequestBody GeneratePostContentRequest request){

        GeneratePostContentDto dto = controllerHelper.convertRequestToGenerateContentDto(request);
        TextCortexResponse apiResponse = postService.generatePostContent(dto);
        return controllerHelper.convertTextCortexResponseToPostContentResponse(apiResponse);

    }
}
