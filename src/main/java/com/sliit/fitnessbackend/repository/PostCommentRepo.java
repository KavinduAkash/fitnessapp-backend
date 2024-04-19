package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.PostComment;
import com.sliit.fitnessbackend.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepo extends JpaRepository<PostComment, Integer> {
}
