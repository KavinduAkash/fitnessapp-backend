package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.MealPlan;
import com.sliit.fitnessbackend.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlaneRepo extends JpaRepository<MealPlan, Integer> {
}
