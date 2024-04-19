package com.sliit.fitnessbackend.dto.request;

import com.sliit.fitnessbackend.dto.MealDTO;
import com.sliit.fitnessbackend.enums.MealStatus;

import java.util.Date;
import java.util.List;

public class MealPlanSaveRequestDTO {
    private String title;
    private String description;
    private boolean isCurrent;
    private List<MealDTO> meals;

    public MealPlanSaveRequestDTO() {
    }

    public MealPlanSaveRequestDTO(String title, String description, boolean isCurrent, List<MealDTO> meals) {
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.meals = meals;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public List<MealDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<MealDTO> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "MealPlanSaveRequestDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCurrent=" + isCurrent +
                ", meals=" + meals +
                '}';
    }
}
