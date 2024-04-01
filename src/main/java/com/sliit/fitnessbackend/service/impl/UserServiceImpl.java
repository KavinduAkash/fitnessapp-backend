package com.sliit.fitnessbackend.service.impl;

import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.exception.UserException;
import com.sliit.fitnessbackend.repository.OurUserRepo;
import com.sliit.fitnessbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public OurUserRepo ourUserRepo;

    @Override
    public UserDTO getMyProfile() {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);
            System.out.println(authentication.getName());

            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if(byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            OurUsers ourUsers = byEmail.get();

            /* OurUsers(Integer id, String firstName, String lastName, Date dob, String email, String password, String role,
                    String visibility, String status, String gender, String profilePic) */
            return new UserDTO(
                    ourUsers.getId(),
                    ourUsers.getFirstName(),
                    ourUsers.getLastName(),
                    ourUsers.getDob(),
                    ourUsers.getEmail(),
                    ourUsers.getGender(),
                    null,
                    ourUsers.getRole(),
                    ourUsers.getVisibility(),
                    ourUsers.getStatus(),
                    ourUsers.getProfilePic()
            );

        } catch (Exception e) {
            throw e;
        }

    }
}
