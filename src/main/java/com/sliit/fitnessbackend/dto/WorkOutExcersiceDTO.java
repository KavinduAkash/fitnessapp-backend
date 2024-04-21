package com.sliit.fitnessbackend.dto;

import com.sliit.fitnessbackend.entity.Excersice;
import com.sliit.fitnessbackend.entity.WorkOut;
import jakarta.persistence.*;

public class WorkOutExcersiceDTO {
    private Integer id;
    private Integer excersiceId;
    private String value;

    public WorkOutExcersiceDTO() {
    }

    public WorkOutExcersiceDTO(Integer id, Integer excersiceId, String value) {
        this.id = id;
        this.excersiceId = excersiceId;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExcersiceId() {
        return excersiceId;
    }

    public void setExcersiceId(Integer excersiceId) {
        this.excersiceId = excersiceId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WorkOutExcersiceDTO{" +
                "id=" + id +
                ", excersiceId=" + excersiceId +
                ", value='" + value + '\'' +
                '}';
    }
}
