package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.UserDTO;
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
                    new ErrorMessageResponseDTO(true, 500, "User's profile is updated successfully!"), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
