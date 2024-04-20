package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.MealPlanDTO;
import com.sliit.fitnessbackend.dto.request.MealPlanSaveRequestDTO;
import com.sliit.fitnessbackend.dto.response.CommonDataResponseDTO;
import com.sliit.fitnessbackend.service.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/meal")
public class MealController {

    @Autowired
    public MealPlanService mealPlanService;

    @PostMapping
    public ResponseEntity createMealPlan(@RequestBody MealPlanSaveRequestDTO mealPlan) {
        boolean save = mealPlanService.addMealPlan(mealPlan);
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "Meal plan created successfully!", ""
                ), HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity getMyMealPlan() {
        List<MealPlanDTO> mealMyPlans = mealPlanService.getMealMyPlans();
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "Meal plan created successfully!", mealMyPlans
                ), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMealPlan() {
        List<MealPlanDTO> mealMyPlans = mealPlanService.getMealMyPlans();
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "Meal plan created successfully!", mealMyPlans
                ), HttpStatus.OK);
    }
}
