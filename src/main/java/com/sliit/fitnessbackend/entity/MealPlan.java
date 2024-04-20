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
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private OurUsers user;
    private Date date;

    public MealPlan() {
    }

    public MealPlan(Integer id, String title, String description, boolean isCurrent, MealStatus status, OurUsers user, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.status = status;
        this.user = user;
        this.date = date;
    }

    public MealPlan(String title, String description, boolean isCurrent, MealStatus status, OurUsers user, Date date) {
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.status = status;
        this.user = user;
        this.date = date;
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

    public MealStatus getStatus() {
        return status;
    }

    public void setStatus(MealStatus status) {
        this.status = status;
    }

    public OurUsers getUser() {
        return user;
    }

    public void setUser(OurUsers user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
