package com.springboot.blog.controller;

import com.springboot.blog.common.WsPath;
import com.springboot.blog.common.dto.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {


    @Autowired
    private PostService postService;


    @PostMapping(WsPath.CREATE_POST)
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post){

        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);

    }
}
