package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.dto.request.UserSignUpRequestDTO;
import com.sliit.fitnessbackend.dto.response.CommonDataResponseDTO;
import com.sliit.fitnessbackend.dto.response.CommonResponseDTO;
import com.sliit.fitnessbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user2")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/test")
    public ResponseEntity<CommonDataResponseDTO> getUserData(){
        UserDTO myProfile = userService.getMyProfile();
        return new ResponseEntity<>(new CommonDataResponseDTO<>(true, "User account created successfully!", myProfile), HttpStatus.OK);
    }
}
