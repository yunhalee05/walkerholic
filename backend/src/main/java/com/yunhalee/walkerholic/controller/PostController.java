package com.yunhalee.walkerholic.controller;

import com.yunhalee.walkerholic.dto.PostCreateDTO;
import com.yunhalee.walkerholic.dto.PostDTO;
import com.yunhalee.walkerholic.dto.UserPostDTO;
import com.yunhalee.walkerholic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/save")
    public String savePost(@RequestParam(value = "id", required = false)Integer id,
                           @RequestParam("content")String content,
                           @RequestParam("userId")Integer userId,
                           @RequestParam(value = "multipartFile", required = false)List<MultipartFile> multipartFiles){
        PostCreateDTO postCreateDTO = new PostCreateDTO(id, content, userId);
        return postService.savePost(postCreateDTO,multipartFiles);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id")String id){
        Integer postId = Integer.parseInt(id);
        return new ResponseEntity<PostDTO>(postService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getUserPosts(@PathVariable("id")String id){
        Integer userId = Integer.parseInt(id);
        return new ResponseEntity<HashMap<String,Object>>(postService.getUserPosts(userId),HttpStatus.OK);
    }

    @GetMapping("/posts/discover/{page}/{id}")
    public ResponseEntity<?> getPostsByRandom(@PathVariable("page")String page,@PathVariable("id")String id){
        Integer pageNumber = Integer.parseInt(page);
        Integer userId = Integer.parseInt(id);
        return new ResponseEntity<HashMap<String, Object>>(postService.getPostsByRandom(pageNumber, userId), HttpStatus.OK);
    }

    @GetMapping("/posts/follow/{page}/{id}")
    public ResponseEntity<?> getPostsByFollowings(@PathVariable("page")String page,@PathVariable("id")String id){
        Integer pageNumber = Integer.parseInt(page);
        Integer userId = Integer.parseInt(id);
        return new ResponseEntity<HashMap<String, Object>>(postService.getPostsByFollowings(pageNumber, userId), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable("id")String id){
        Integer postId = Integer.parseInt(id);
        return postService.deletePost(postId);
    }
}
