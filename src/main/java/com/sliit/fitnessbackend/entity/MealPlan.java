package com.sliit.fitnessbackend.entity;

import com.sliit.fitnessbackend.enums.MealStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "meal_plan")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private boolean isCurrent;
    private MealStatus status;
    private Date date;

    public MealPlan() {
    }

    public MealPlan(Integer id, String title, String description, boolean isCurrent, MealStatus status, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.status = status;
        this.date = date;
    }

    public MealPlan(String title, String description, boolean isCurrent, MealStatus status, Date date) {
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.status = status;
        this.date = date;
    }
}
