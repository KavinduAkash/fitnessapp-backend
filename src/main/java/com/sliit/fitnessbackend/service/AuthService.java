package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.ReqRes;
import com.sliit.fitnessbackend.dto.request.ResetPasswordRequestDTO;
import com.sliit.fitnessbackend.dto.request.UserSignUpRequestDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface AuthService {
    public boolean signUp(UserSignUpRequestDTO registrationRequest);
    public ReqRes signIn(ReqRes signinRequest);
    public ReqRes refreshToken(ReqRes refreshTokenReqiest);
    public boolean resetPassword(ResetPasswordRequestDTO passwordReset);
}
