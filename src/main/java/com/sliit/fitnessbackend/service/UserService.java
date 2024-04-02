package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    public UserDTO getMyProfile();
    public UserDTO updateMyProfile(UserDTO userDTO);
    public UserDTO updateMyProfilePic(MultipartFile file) throws IOException;

}
