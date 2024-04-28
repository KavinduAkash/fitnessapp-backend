package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.Follower;
import com.sliit.fitnessbackend.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepo extends JpaRepository<Follower, Integer>  {
}
