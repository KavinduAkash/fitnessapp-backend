package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.MealPlan;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.Post;
import com.sliit.fitnessbackend.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealPlaneRepo extends JpaRepository<MealPlan, Integer> {
    @Query("SELECT mp from MealPlan mp WHERE mp.user=:user")
    List<MealPlan> getMealPlansByUser(@Param("user") OurUsers user);

    @Query("SELECT mp FROM MealPlan mp ORDER BY mp.date DESC")
    List<Post> getLatestMealPlans();
}
