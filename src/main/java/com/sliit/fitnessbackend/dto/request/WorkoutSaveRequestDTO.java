package com.sliit.fitnessbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sliit.fitnessbackend.dto.WorkOutExcersiceDTO;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.enums.WorkOutStatus;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

public class WorkoutSaveRequestDTO {
    private String title;
    private String description;
    private boolean isCurrent;
    private List<WorkOutExcersiceDTO> exercises;

    public WorkoutSaveRequestDTO() {
    }

    public WorkoutSaveRequestDTO(String title, String description, boolean isCurrent, List<WorkOutExcersiceDTO> exercises) {
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.exercises = exercises;
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

    public List<WorkOutExcersiceDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkOutExcersiceDTO> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "WorkoutSaveRequestDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCurrent=" + isCurrent +
                ", exercises=" + exercises +
                '}';
    }
}
