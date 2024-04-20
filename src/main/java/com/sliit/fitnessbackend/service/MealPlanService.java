package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.MealPlanDTO;
import com.sliit.fitnessbackend.dto.request.MealPlanSaveRequestDTO;

import java.util.List;

public interface MealPlanService {
    public boolean addMealPlan(MealPlanSaveRequestDTO mealPlan);
    public List<MealPlanDTO> getMealMyPlans();
    public List<MealPlanDTO> getMealPlans();
}
