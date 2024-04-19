package com.sliit.fitnessbackend.service.impl;

import com.sliit.fitnessbackend.dto.MealDTO;
import com.sliit.fitnessbackend.dto.request.MealPlanSaveRequestDTO;
import com.sliit.fitnessbackend.entity.Meal;
import com.sliit.fitnessbackend.entity.MealPlan;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.enums.MealStatus;
import com.sliit.fitnessbackend.exception.UserException;
import com.sliit.fitnessbackend.repository.MealPlaneRepo;
import com.sliit.fitnessbackend.repository.MealRepo;
import com.sliit.fitnessbackend.repository.OurUserRepo;
import com.sliit.fitnessbackend.service.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    public OurUserRepo ourUserRepo;

    @Autowired
    public MealPlaneRepo mealPlaneRepo;

    @Autowired
    public MealRepo mealRepo;

    @Override
    public boolean addMealPlan(MealPlanSaveRequestDTO mealPlan) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // get auth user
            OurUsers authUsers = byEmail.get();

            // MealPlan(String title, String description, boolean isCurrent, MealStatus status, Date date)
            MealPlan save = mealPlaneRepo.save(new MealPlan(mealPlan.getTitle(), mealPlan.getDescription(), mealPlan.isCurrent(), MealStatus.ACTIVE, new Date()));

            for (MealDTO meal : mealPlan.getMeals()) {
                // Meal(String mealName, String description, MealPlan mealPlan)
                mealRepo.save(new Meal(meal.getMeal_name(), meal.getDescription(), save));
            }

            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
