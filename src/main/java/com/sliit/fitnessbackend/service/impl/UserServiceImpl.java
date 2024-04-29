package com.sliit.fitnessbackend.service.impl;

import com.sliit.fitnessbackend.constant.ApplicationConstant;
import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.dto.request.FollowerRequestDTO;
import com.sliit.fitnessbackend.entity.Follower;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.exception.FileException;
import com.sliit.fitnessbackend.exception.UserException;
import com.sliit.fitnessbackend.repository.FollowerRepo;
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

    @Autowired
    public FollowerRepo followerRepo;

    @Override
    public UserDTO getMyProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);
            System.out.println(authentication.getName());

            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            OurUsers ourUsers = byEmail.get();

            return prepareUserDTO(ourUsers);
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

            return prepareUserDTO(save);
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

            return prepareUserDTO(save);
        } catch (IOException ex) {
            throw new FileException(400, "error");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<UserDTO> searchUsers(String search) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");
            OurUsers user = byEmail.get();

            List<UserDTO> userList = new ArrayList<>();
            List<OurUsers> ourUsers = ourUserRepo.searchUsers(search);
            for (OurUsers user1 : ourUsers) {
                userList.add(prepareUserDTOWithVisibility(user1, user));
            }
            return userList;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDTO getSpecificUserData(Integer id) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");
            OurUsers user = byEmail.get();

            Optional<OurUsers> userById = ourUserRepo.getUserById(id);
            if (userById.isEmpty()) throw new UserException(404, "User not found");

            OurUsers user1 = userById.get();
            return prepareUserDTOWithVisibility(user1, user);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean followUser(FollowerRequestDTO follow) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");
            OurUsers user = byEmail.get();

            // check follower
            Optional<OurUsers> userById = ourUserRepo.getUserById(follow.getFollowerId());
            if (userById.isEmpty()) throw new UserException(404, "User not found");
            OurUsers follower = userById.get();

            // save follower
            followerRepo.save(new Follower(user, follower, new Date()));

            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<UserDTO> getUsers(String search) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");
            OurUsers user = byEmail.get();

            List<UserDTO> userList = new ArrayList<>();
            List<OurUsers> ourUsers = ourUserRepo.searchUsers2(search);
            for (OurUsers user1 : ourUsers) {
                userList.add(prepareUserDTOWithVisibility(user1, user));
            }
            return userList;
        } catch (Exception e) {
            throw e;
        }
    }

    private UserDTO prepareUserDTO(OurUsers user) {
        /* OurUsers(Integer id, String firstName, String lastName, Date dob, String email, String password, String role,
          String visibility, String status, String gender, String profilePic) */
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDob(),
                user.getEmail(),
                null,
                user.getGender(),
                user.getRole(),
                user.getVisibility(),
                user.getStatus(),
                ApplicationConstant.IMAGE_URL + user.getProfilePic()
        );
    }

    private UserDTO prepareUserDTOWithVisibility(OurUsers user, OurUsers myAccount) {

        Optional<Follower> follower = followerRepo.myFollower(myAccount, user);
        Optional<Follower> followering = followerRepo.myFollowing(myAccount, user);

        return user.getVisibility().equals("PRIVATE") ? // check profile visibility
                /* OurUsers(Integer id, String firstName, String lastName, Date dob, String email, String password, String role,
                        String visibility, String status, String gender, String profilePic) */
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
                        user.getProfilePic(),
                        !follower.isEmpty(),
                        !followering.isEmpty()
                )
                :
                /* OurUsers(Integer id, String firstName, String lastName, Date dob, String email, String password, String role,
                        String visibility, String status, String gender, String profilePic) */
                new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getDob(),
                        null,
                        null,
                        user.getGender(),
                        user.getRole(),
                        user.getVisibility(),
                        user.getStatus(),
                        user.getProfilePic(),
                        !follower.isEmpty(),
                        !followering.isEmpty()
                );
    }
}
