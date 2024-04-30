package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.dto.request.FollowerRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    public UserDTO getMyProfile();
    public UserDTO updateMyProfile(UserDTO userDTO);
    public boolean deleteMyProfile();
    public UserDTO updateMyProfilePic(MultipartFile file) throws IOException;
    public List<UserDTO> searchUsers(String search);
    public UserDTO getSpecificUserData(Integer id);
    public boolean followUser(FollowerRequestDTO follow);
    public List<UserDTO> getUsers(String search);

}
