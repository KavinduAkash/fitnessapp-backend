package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.Follower;
import com.sliit.fitnessbackend.entity.MealPlan;
import com.sliit.fitnessbackend.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FollowerRepo extends JpaRepository<Follower, Integer>  {
    @Query("SELECT f FROM Follower f WHERE f.user=:user AND f.follower=:follower")
    Optional<Follower> myFollower(@Param("user")OurUsers user, @Param("follower")OurUsers follower);

    @Query("SELECT f FROM Follower f WHERE f.user=:follower AND f.follower=:user")
    Optional<Follower> myFollowing(@Param("user")OurUsers user, @Param("follower")OurUsers follower);
}
