package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.ReqRes;
import com.sliit.fitnessbackend.dto.request.UserSignUpRequestDTO;
import com.sliit.fitnessbackend.dto.response.CommonResponseDTO;
import com.sliit.fitnessbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDTO> signUp(@RequestBody UserSignUpRequestDTO signUpRequest){
        boolean result = authService.signUp(signUpRequest);
        return new ResponseEntity<>(new CommonResponseDTO(result, "User account created successfully!"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
