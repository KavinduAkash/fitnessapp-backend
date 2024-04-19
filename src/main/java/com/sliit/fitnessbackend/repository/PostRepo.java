package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
