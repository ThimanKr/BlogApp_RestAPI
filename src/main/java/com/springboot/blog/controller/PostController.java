package com.springboot.blog.controller;

import com.springboot.blog.common.WsPath;
import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** The PostController is the rest api interface for POST resources  */
@RestController
public class PostController {


    @Autowired
    private PostService postService;


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
     * @return updated post
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
     * getAllPosts is the method to get all the posts
     * @return List of PostDto
     */
    @GetMapping(WsPath.POSTS)
    public List<PostDto> getAllPosts(){

        return postService.getAllPosts();
    }
}
