package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.ReqRes;
import com.sliit.fitnessbackend.dto.request.UserSignUpRequestDTO;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.exception.UserException;
import com.sliit.fitnessbackend.repository.OurUserRepo;
import com.sliit.fitnessbackend.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean signUp(UserSignUpRequestDTO registrationRequest){
        try {

            if(registrationRequest.getFirstName()==null || registrationRequest.getLastName()==null || registrationRequest.getEmail()==null || registrationRequest.getDob()==null)
                throw new UserException(422, "Invalid data");

            // check account availability
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(registrationRequest.getEmail());
            if (!byEmail.isEmpty())
                throw new UserException(409, "An account already exists with given email!");

            OurUsers ourUsers = new OurUsers(); // define entity object

            // assign values
            ourUsers.setFirstName(registrationRequest.getFirstName());
            ourUsers.setLastName(registrationRequest.getLastName());
            ourUsers.setDob(registrationRequest.getDob());
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole("USER"); // USER
            ourUsers.setStatus("ACTIVE"); // ACTIVE or DELETED
            ourUsers.setVisibility("PUBLIC"); // PUBLIC or PRIVATE

            // save user object in db
            OurUsers ourUserResult = ourUserRepo.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getId()>0) {
                return true;
            } else {
                throw new UserException(100, "Something went wrong!");
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public ReqRes signIn(ReqRes signinRequest){
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
            var user = ourUserRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
            System.out.println("USER IS: "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        }catch (Exception e){

            throw new UserException(404, "User Not Found");

//            response.setStatusCode(500);
//            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        OurUsers users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
