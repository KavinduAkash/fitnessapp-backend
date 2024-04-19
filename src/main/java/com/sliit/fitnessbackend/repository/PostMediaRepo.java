package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.Post;
import com.sliit.fitnessbackend.entity.PostMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMediaRepo extends JpaRepository<PostMedia, Integer> {
}
