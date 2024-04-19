package com.sliit.fitnessbackend.dto;

public class MealDTO {
    private Integer id;
    private String meal_name;
    private String description;

    public MealDTO() {
    }

    public MealDTO(Integer id, String meal_name, String description) {
        this.id = id;
        this.meal_name = meal_name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MealDTO{" +
                "id=" + id +
                ", meal_name='" + meal_name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
