package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.Post;
import com.sliit.fitnessbackend.entity.PostMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostMediaRepo extends JpaRepository<PostMedia, Integer> {
    @Query("SELECT p FROM PostMedia p WHERE p.post=:post")
    List<PostMedia> getMyPostMedias(@Param("post") Post post);
}
