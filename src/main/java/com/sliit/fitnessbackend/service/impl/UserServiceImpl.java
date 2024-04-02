package com.sliit.fitnessbackend.service.impl;

import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.exception.FileException;
import com.sliit.fitnessbackend.exception.UserException;
import com.sliit.fitnessbackend.repository.OurUserRepo;
import com.sliit.fitnessbackend.service.UserService;
import com.sliit.fitnessbackend.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

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

    @Override
    public UserDTO updateMyProfile(UserDTO userDTO) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // validate user's data
            if (userDTO.getFirstName() == null || userDTO.getLastName() == null || userDTO.getEmail() == null || userDTO.getDob() == null || userDTO.getGender() == null)
                throw new UserException(422, "Invalid data");

            // get user
            OurUsers ourUsers = byEmail.get();

            // validate user
            if (!userDTO.getEmail().equals(ourUsers.getEmail())) throw new UserException(401, "Unauthorized action");

            // set new user data
            ourUsers.setFirstName(userDTO.getFirstName());
            ourUsers.setLastName(userDTO.getLastName());
            ourUsers.setDob(userDTO.getDob());
            ourUsers.setGender(userDTO.getGender());
            ourUsers.setVisibility(userDTO.getVisibility());

            // update user with new data
            OurUsers save = ourUserRepo.save(ourUsers);

            /* OurUsers(Integer id, String firstName, String lastName, Date dob, String email, String password, String role,
                    String visibility, String status, String gender, String profilePic) */
            return new UserDTO(
                    save.getId(),
                    save.getFirstName(),
                    save.getLastName(),
                    save.getDob(),
                    save.getEmail(),
                    save.getGender(),
                    null,
                    save.getRole(),
                    save.getVisibility(),
                    save.getStatus(),
                    save.getProfilePic()
            );
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDTO updateMyProfilePic(MultipartFile file) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // upload profile pic
            String fileName = FileManager.uploadProfilePic(file);

            // get user
            OurUsers ourUsers = byEmail.get();

            // set profile pic value
            ourUsers.setProfilePic(fileName);

            // update the user
            OurUsers save = ourUserRepo.save(ourUsers);

            /* OurUsers(Integer id, String firstName, String lastName, Date dob, String email, String password, String role,
                    String visibility, String status, String gender, String profilePic) */
            return new UserDTO(
                    save.getId(),
                    save.getFirstName(),
                    save.getLastName(),
                    save.getDob(),
                    save.getEmail(),
                    save.getGender(),
                    null,
                    save.getRole(),
                    save.getVisibility(),
                    save.getStatus(),
                    save.getProfilePic()
            );
        } catch (IOException ex) {
            throw new FileException(400, "error");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<UserDTO> searchUsers(String search) {
        try {
            List<UserDTO> userList = new ArrayList<>();
            List<OurUsers> ourUsers = ourUserRepo.searchUsers(search);
            for (OurUsers user : ourUsers) {
                userList.add(
                        /* OurUsers(Integer id, String firstName, String lastName, Date dob, String email, String password, String role,
                        String visibility, String status, String gender, String profilePic) */
                        user.getVisibility().equals("PRIVATE") ? // check profile visibility
                                new UserDTO(
                                        user.getId(),
                                        user.getFirstName(),
                                        user.getLastName(),
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        user.getVisibility(),
                                        user.getStatus(),
                                        user.getProfilePic()
                                )
                                :
                                new UserDTO(
                                        user.getId(),
                                        user.getFirstName(),
                                        user.getLastName(),
                                        user.getDob(),
                                        null,
                                        user.getGender(),
                                        null,
                                        user.getRole(),
                                        user.getVisibility(),
                                        user.getStatus(),
                                        user.getProfilePic()
                                )

                );
            }
            return userList;
        } catch (Exception e) {
            throw e;
        }
    }
}
