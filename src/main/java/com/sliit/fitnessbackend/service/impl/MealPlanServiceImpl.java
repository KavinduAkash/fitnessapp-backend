package com.sliit.fitnessbackend.service.impl;

import com.sliit.fitnessbackend.dto.MealDTO;
import com.sliit.fitnessbackend.dto.MealPlanDTO;
import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.dto.request.MealPlanSaveRequestDTO;
import com.sliit.fitnessbackend.entity.Meal;
import com.sliit.fitnessbackend.entity.MealPlan;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.PostLike;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            MealPlan save = mealPlaneRepo.save(new MealPlan(mealPlan.getTitle(), mealPlan.getDescription(), mealPlan.isCurrent(), MealStatus.ACTIVE, authUsers, new Date()));

            for (MealDTO meal : mealPlan.getMeals()) {
                // Meal(String mealName, String description, MealPlan mealPlan)
                mealRepo.save(new Meal(meal.getMeal_name(), meal.getDescription(), save));
            }

            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<MealPlanDTO> getMealMyPlans() {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // get auth user
            OurUsers authUsers = byEmail.get();

            List<MealPlanDTO> mealPlanDTOS = new ArrayList<>();
            List<MealPlan> mealPlansByUser = mealPlaneRepo.getMealPlansByUser(authUsers);
            for (MealPlan mealPlan : mealPlansByUser) {
                List<MealDTO> mealDTOS = new ArrayList<>();
                List<Meal> mealByMealPlan = mealRepo.getMealByMealPlan(mealPlan);
                for (Meal meal : mealByMealPlan) {
                    // MealDTO(Integer id, String meal_name, String description)
                    mealDTOS.add(new MealDTO(meal.getId(), meal.getMealName(), meal.getDescription()));
                }
                // MealPlanDTO(Integer id, String title, String description, boolean isCurrent, UserDTO userDTO, List<MealDTO> meals)
                mealPlanDTOS.add(new MealPlanDTO(mealPlan.getId(), mealPlan.getTitle(), mealPlan.getDescription(),
                        mealPlan.isCurrent(), prepreUserDTO(mealPlan.getUser()), mealDTOS));
            }

            return mealPlanDTOS;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<MealPlanDTO> getMealPlans() {
        try {
            List<MealPlanDTO> mealPlanDTOS = new ArrayList<>();
            List<MealPlan> mealPlansByUser = mealPlaneRepo.getLatestMealPlans();
            for (MealPlan mealPlan : mealPlansByUser) {
                List<MealDTO> mealDTOS = new ArrayList<>();
                List<Meal> mealByMealPlan = mealRepo.getMealByMealPlan(mealPlan);
                for (Meal meal : mealByMealPlan) {
                    // MealDTO(Integer id, String meal_name, String description)
                    mealDTOS.add(new MealDTO(meal.getId(), meal.getMealName(), meal.getDescription()));
                }
                // MealPlanDTO(Integer id, String title, String description, boolean isCurrent, UserDTO userDTO, List<MealDTO> meals)
                mealPlanDTOS.add(new MealPlanDTO(mealPlan.getId(), mealPlan.getTitle(), mealPlan.getDescription(),
                        mealPlan.isCurrent(), prepreUserDTO(mealPlan.getUser()), mealDTOS));
            }

            return mealPlanDTOS;
        } catch (Exception e) {
            throw e;
        }
    }

    private UserDTO prepreUserDTO(OurUsers user) {
        try {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    user.getVisibility(),
                    user.getStatus(),
                    user.getProfilePic()
            );
            return userDTO;
        } catch (Exception e) {
            throw e;
        }
    }
}
