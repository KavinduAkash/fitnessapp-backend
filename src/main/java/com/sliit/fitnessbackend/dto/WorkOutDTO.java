package com.sliit.fitnessbackend.dto;

import java.util.List;

public class WorkOutDTO {
    private Integer id;
    private String title;
    private String description;
    private boolean isCurrent;
    private List<ExcersiceDTO> exercises;

    private UserDTO userDTO;

    public WorkOutDTO() {
    }

    public WorkOutDTO(Integer id, String title, String description, boolean isCurrent, List<ExcersiceDTO> exercises) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.exercises = exercises;
    }

    public WorkOutDTO(Integer id, String title, String description, boolean isCurrent, List<ExcersiceDTO> exercises, UserDTO userDTO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCurrent = isCurrent;
        this.exercises = exercises;
        this.userDTO = userDTO;
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

    public List<ExcersiceDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExcersiceDTO> exercises) {
        this.exercises = exercises;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
