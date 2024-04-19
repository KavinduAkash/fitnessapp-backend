package com.sliit.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mealName;
    private String description;
    @ManyToOne
    @JoinColumn(name = "meal_plan", nullable = false)
    private MealPlan mealPlan;

    public Meal() {
    }

    public Meal(Integer id, String mealName, String description) {
        this.id = id;
        this.mealName = mealName;
        this.description = description;
    }

    public Meal(String mealName, String description, MealPlan mealPlan) {
        this.mealName = mealName;
        this.description = description;
        this.mealPlan = mealPlan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }
}
