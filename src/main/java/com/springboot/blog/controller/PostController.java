package com.springboot.blog.controller;

import com.springboot.blog.common.WsPath;
import com.springboot.blog.common.constants.PostConstants;
import com.springboot.blog.common.dto.GeneratePostContentDto;
import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.controller.helper.PostControllerHelper;
import com.springboot.blog.controller.payload.GeneratePostContentRequest;
import com.springboot.blog.controller.payload.GeneratePostContentResponse;
import com.springboot.blog.controller.payload.PostResponse;
import com.springboot.blog.controller.payload.TextCortexResponse;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** The PostController is the rest api interface for POST resources  */
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
    @PostMapping(WsPath.CREATE_POST)
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post){

        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);

    }

    /**
     * getPostById method is to get post data by post id
     * @param id post Id
     * @return post
     */
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
    @PutMapping(WsPath.UPDATE_POST)
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id){

        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    /**
     * deletePost is the method to delete a post by post id
     * @param id post Id
     * @return ResponseEntity
     */
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
    @GetMapping(WsPath.POSTS)
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = PostConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PostConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PostConstants.DEFAULT_SORT_BY, required = false) String sortBy){

        return postService.getAllPosts(pageNo, pageSize, sortBy);
    }

    /**
     * generatePostText is the method to generate text for given title and keywords using 3rd party API called TextCortex
     * @param request object of postTitle and postKeywords
     * @return GeneratePostContentResponse with generated text and success status
     */
    @PostMapping(WsPath.GENERATE_POST)
    public GeneratePostContentResponse generatePostText(@RequestBody GeneratePostContentRequest request){

        GeneratePostContentDto dto = controllerHelper.convertRequestToGenerateContentDto(request);
        TextCortexResponse apiResponse = postService.generatePostContent(dto);
        return controllerHelper.convertTextCortexResponseToPostContentResponse(apiResponse);

    }
}
