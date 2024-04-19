package com.sliit.fitnessbackend.service.impl;

import com.sliit.fitnessbackend.dto.PostDTO;
import com.sliit.fitnessbackend.dto.PostMediaDTO;
import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.Post;
import com.sliit.fitnessbackend.entity.PostMedia;
import com.sliit.fitnessbackend.enums.PostStatus;
import com.sliit.fitnessbackend.exception.FileException;
import com.sliit.fitnessbackend.exception.UserException;
import com.sliit.fitnessbackend.repository.OurUserRepo;
import com.sliit.fitnessbackend.repository.PostMediaRepo;
import com.sliit.fitnessbackend.repository.PostRepo;
import com.sliit.fitnessbackend.service.PostService;
import com.sliit.fitnessbackend.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PostServiceImpl implements PostService {

    @Autowired
    public OurUserRepo ourUserRepo;

    @Autowired
    public PostRepo postRepo;

    @Autowired
    public PostMediaRepo postMediaRepo;

    @Override
    public void addNewPost(MultipartFile file1, MultipartFile file2, MultipartFile file3, String note) {
        try {

            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            ArrayList<String> images = new ArrayList<>();

            // upload profile pic
            if(file1!=null) {
                String fileName1 = FileManager.uploadProfilePic(file1);
                images.add(fileName1);
            }
            if(file2!=null) {
                String fileName2 = FileManager.uploadProfilePic(file2);
                images.add(fileName2);
            }
            if(file3!=null) {
                String fileName3 = FileManager.uploadProfilePic(file3);
                images.add(fileName3);
            }


            // get user
            OurUsers ourUsers = byEmail.get();

            // Post(OurUsers user, Date date, String description, PostStatus status)
            Post post = new Post(
                    ourUsers,
                    new Date(),
                    note,
                    PostStatus.ACTIVE
            );
            Post save = postRepo.save(post);

            System.out.println("s: " + save.toString());

            for (String image : images) {
                PostMedia postMedia = new PostMedia(
                        save,
                        image
                );
                postMediaRepo.save(postMedia);
            }

        } catch (IOException ex) {
            System.out.println("1: " + ex.getMessage());
            throw new FileException(400, "error");
        } catch (Exception e) {
            System.out.println("2: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<PostDTO> getMyPosts() {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // get user
            OurUsers ourUsers = byEmail.get();

            UserDTO userDTO = new UserDTO(
                    ourUsers.getId(),
                    ourUsers.getFirstName(),
                    ourUsers.getLastName(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    ourUsers.getVisibility(),
                    ourUsers.getStatus(),
                    ourUsers.getProfilePic()
            );

            List<PostDTO> myPostsRes = new ArrayList<>();
            List<Post> myPosts = postRepo.getMyPosts(ourUsers);

            for (Post post: myPosts) {

                List<PostMedia> myPostMedias = postMediaRepo.getMyPostMedias(post);
                List<PostMediaDTO> postMediaDTOS = new ArrayList<>();
                for (PostMedia media : myPostMedias) {
                    postMediaDTOS.add(new PostMediaDTO(media.getId(), media.getUrl()));
                }

                // new PostDTO(id, date, description, images, user);
                myPostsRes.add(new PostDTO(post.getId(), post.getDate(), post.getDescription(), postMediaDTOS, userDTO));
            }

            return myPostsRes;
        } catch (Exception e) {
            throw e;
        }
    }
}
