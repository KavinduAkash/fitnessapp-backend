package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.MealPlanDTO;
import com.sliit.fitnessbackend.dto.request.MealPlanSaveRequestDTO;
import com.sliit.fitnessbackend.dto.request.MealPlanUpdateRequestDTO;

import java.util.List;

public interface MealPlanService {
    public boolean addMealPlan(MealPlanSaveRequestDTO mealPlan);
    public List<MealPlanDTO> getMealMyPlans();
    public List<MealPlanDTO> getMealMyPlans(Integer id);
    public List<MealPlanDTO> getMealPlans();
    public boolean updateMealPlan(MealPlanUpdateRequestDTO mealPlan);
    public boolean deleteMealPlan(Integer mealPlanId);
}
