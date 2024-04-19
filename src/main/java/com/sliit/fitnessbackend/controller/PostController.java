package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.PostDTO;
import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.dto.response.CommonDataResponseDTO;
import com.sliit.fitnessbackend.dto.response.ErrorMessageResponseDTO;
import com.sliit.fitnessbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity addNewPost(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file2") MultipartFile file3, @RequestParam("note") String note) {
        postService.addNewPost(file1, file2, file3, note);
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "User's profile is updated successfully!", ""
                ), HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<CommonDataResponseDTO> getMyPosts(){
        List<PostDTO> myPosts = postService.getMyPosts();
        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, null, myPosts), HttpStatus.OK);
    }


    @GetMapping("/feed")
    public ResponseEntity<CommonDataResponseDTO> getFeedPosts(){
        List<PostDTO> myPosts = postService.getFeedPosts();
        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, null, myPosts), HttpStatus.OK);
    }



}