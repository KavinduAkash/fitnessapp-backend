package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.CommentDTO;
import com.sliit.fitnessbackend.dto.PostDTO;
import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.dto.request.PostCommentRequestDTO;
import com.sliit.fitnessbackend.dto.response.CommonDataResponseDTO;
import com.sliit.fitnessbackend.dto.response.CommonResponseDTO;
import com.sliit.fitnessbackend.dto.response.ErrorMessageResponseDTO;
import com.sliit.fitnessbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    @PatchMapping("/like/{post}")
    public ResponseEntity<CommonResponseDTO> likePost(@PathVariable Integer post){
        postService.likePosts(post);
        return new ResponseEntity<>(new CommonResponseDTO(true, "Post has been liked successfully!"), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommonResponseDTO> addPostComment(@RequestBody PostCommentRequestDTO postComment){
        postService.addPostComment(postComment);
        return new ResponseEntity<>(new CommonResponseDTO(true, "Post comment has been added successfully!"), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<CommonResponseDTO> deletePostComment(@PathVariable Integer commentId){
        postService.deletePostComment(commentId);
        return new ResponseEntity<>(new CommonResponseDTO(true, "Post comment has been deleted successfully!"), HttpStatus.OK);
    }

    @GetMapping("/comment/post/{postId}")
    public ResponseEntity<CommonDataResponseDTO> getPostComment(@PathVariable Integer postId){
//        List<CommentDTO> postCommentByPost = postService.getPostCommentByPost(postId);
        List<CommentDTO> postCommentByPost = new ArrayList<>();
        System.out.println("--> 6" + postCommentByPost);
//        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, "Post comment are found successfully!", postCommentByPost), HttpStatus.OK);
//        return new ResponseEntity<>(new CommonResponseDTO(true, "Post comment has been deleted successfully!"), HttpStatus.OK);
        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, null, postCommentByPost), HttpStatus.OK);
    }





}
