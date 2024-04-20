package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.Meal;
import com.sliit.fitnessbackend.entity.MealPlan;
import com.sliit.fitnessbackend.entity.Post;
import com.sliit.fitnessbackend.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepo extends JpaRepository<Meal, Integer> {
    @Query("SELECT m FROM Meal m WHERE m.mealPlan=:mealPlan")
    List<PostLike> getMealByMealPlan(@Param("mealPlan") MealPlan mealPlan);
}
