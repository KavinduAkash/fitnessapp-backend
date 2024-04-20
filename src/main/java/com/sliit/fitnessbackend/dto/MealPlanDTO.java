package com.sliit.fitnessbackend.dto;

import java.util.List;

public class MealPlanDTO {
    private Integer id;
    private String title;
    private String description;
    private boolean isCurrent;
    private UserDTO userDTO;
    private List<MealDTO> meals;

    public MealPlanDTO() {
    }

    public MealPlanDTO(Integer id, String title, String description, boolean isCurrent, UserDTO userDTO, List<MealDTO> meals) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.userDTO = userDTO;
        this.meals = meals;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "MealPlanDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCurrent=" + isCurrent +
                ", meals=" + meals +
                '}';
    }
}
