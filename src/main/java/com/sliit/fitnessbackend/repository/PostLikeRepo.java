package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.Post;
import com.sliit.fitnessbackend.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepo extends JpaRepository<PostLike, Integer> {
}
