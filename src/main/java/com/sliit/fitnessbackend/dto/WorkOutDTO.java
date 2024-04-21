package com.sliit.fitnessbackend.dto;

import java.util.List;

public class WorkOutDTO {
    private Integer id;
    private String title;
    private String description;
    private boolean isCurrent;
    private List<WorkOutExcersiceDTO> exercises;

    public WorkOutDTO() {
    }

    public WorkOutDTO(Integer id, String title, String description, boolean isCurrent, List<WorkOutExcersiceDTO> exercises) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.exercises = exercises;
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

    public List<WorkOutExcersiceDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkOutExcersiceDTO> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "WorkOutDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCurrent=" + isCurrent +
                ", exercises=" + exercises +
                '}';
    }
}
