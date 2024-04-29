package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.dto.request.FollowerRequestDTO;
import com.sliit.fitnessbackend.dto.request.UserSignUpRequestDTO;
import com.sliit.fitnessbackend.dto.response.CommonDataResponseDTO;
import com.sliit.fitnessbackend.dto.response.CommonResponseDTO;
import com.sliit.fitnessbackend.dto.response.ErrorMessageResponseDTO;
import com.sliit.fitnessbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/my")
    public ResponseEntity<CommonDataResponseDTO> getUserData(){
        UserDTO myProfile = userService.getMyProfile();
        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, null, myProfile), HttpStatus.OK);
    }

    @PutMapping("/my")
    public ResponseEntity<CommonDataResponseDTO> updateUserData(@RequestBody UserDTO userDTO) {
        UserDTO updateMyProfile = userService.updateMyProfile(userDTO);
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "User's profile is updated successfully!", updateMyProfile
                ), HttpStatus.OK);
    }

    @PatchMapping("/my-pic")
    public ResponseEntity updateUserPic(@RequestParam("file") MultipartFile file) {
        try {
            UserDTO updateMyProfile = userService.updateMyProfilePic(file);
            return new ResponseEntity<>(
                    new CommonDataResponseDTO<>(true, "User's profile is updated successfully!", updateMyProfile
                    ), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(
                    new ErrorMessageResponseDTO(false, 500, "Sorry! Something went wrong"), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{search}")
    public ResponseEntity<CommonDataResponseDTO> searchUsers(@PathVariable String search){
        List<UserDTO> usersList = userService.searchUsers(search);
        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, null, usersList), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CommonDataResponseDTO> getSpecificUserData(@PathVariable Integer id){
        UserDTO user = userService.getSpecificUserData(id);
        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, null, user), HttpStatus.OK);
    }

    @PatchMapping("/follow")
    public ResponseEntity followUsers(@RequestBody FollowerRequestDTO follow) {
        boolean result = userService.followUser(follow);
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "You followed the user successfully!", null
                ), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<CommonDataResponseDTO> getUsers(@RequestParam("search") String search){
        List<UserDTO> user = userService.getUsers(search);
        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, null, user), HttpStatus.OK);
    }

}
