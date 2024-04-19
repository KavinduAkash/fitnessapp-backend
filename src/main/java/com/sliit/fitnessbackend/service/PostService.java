package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.PostDTO;
import com.sliit.fitnessbackend.dto.request.PostCommentRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    public void addNewPost(MultipartFile file1, MultipartFile file2, MultipartFile file3, String note);
    public List<PostDTO> getMyPosts();
    public List<PostDTO> getFeedPosts();
    public boolean likePosts(Integer post);
    public boolean addPostComment(PostCommentRequestDTO postComment);
}
