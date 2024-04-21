package com.sliit.fitnessbackend.dto.request;

import com.sliit.fitnessbackend.dto.MealDTO;

import java.util.List;

public class MealPlanUpdateRequestDTO {
    private Integer id;
    private String title;
    private String description;
    private boolean isCurrent;
    private List<MealDTO> meals;

    public MealPlanUpdateRequestDTO() {
    }

    public MealPlanUpdateRequestDTO(Integer id, String title, String description, boolean isCurrent, List<MealDTO> meals) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
