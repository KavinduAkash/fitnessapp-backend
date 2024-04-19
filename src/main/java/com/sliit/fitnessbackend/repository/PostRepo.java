package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.user=:user")
    List<Post> getMyPosts(@Param("user") OurUsers user);

    @Query("SELECT p FROM Post p ORDER BY p.date DESC")
    List<Post> getLatestPosts();
}
