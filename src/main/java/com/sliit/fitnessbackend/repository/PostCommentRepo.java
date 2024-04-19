package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.Post;
import com.sliit.fitnessbackend.entity.PostComment;
import com.sliit.fitnessbackend.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostCommentRepo extends JpaRepository<PostComment, Integer> {

    @Query("SELECT c FROM PostComment c WHERE c.post=:post")
    List<PostComment> getPostCommentByPost(@Param("post") Post post);

}
