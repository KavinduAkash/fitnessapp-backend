package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.Meal;
import com.sliit.fitnessbackend.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepo extends JpaRepository<Meal, Integer> {
}
