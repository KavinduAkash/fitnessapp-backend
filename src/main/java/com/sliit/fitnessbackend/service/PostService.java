package com.sliit.fitnessbackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    public void addNewPost(MultipartFile file1, MultipartFile file2, MultipartFile file3, String note);
}
