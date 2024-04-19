package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.request.MealPlanSaveRequestDTO;

public interface MealPlanService {
    public boolean addMealPlan(MealPlanSaveRequestDTO mealPlan);
}
