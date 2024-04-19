package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.Post;
import com.sliit.fitnessbackend.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepo extends JpaRepository<PostLike, Integer> {
    @Query("SELECT p FROM PostLike p WHERE p.post=:post")
    List<PostLike> getPostLikes(@Param("post") Post post);
    @Query("SELECT p FROM PostLike p WHERE p.post=:post AND p.user=:user")
    Optional<PostLike> getPostLikesWithUserAndPost(@Param("post") Post post, @Param("user") OurUsers user);
}
