package com.sliit.fitnessbackend.dto;

import com.sliit.fitnessbackend.enums.ExcersiceType;
import com.sliit.fitnessbackend.enums.ExcersiceValues;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ExcersiceDTO {
    private Integer id;
    private String name;
    private ExcersiceType type;
    private ExcersiceValues value;

    private String desc;

    public ExcersiceDTO() {
    }

    public ExcersiceDTO(Integer id, String name, ExcersiceType type, ExcersiceValues value, String desc) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExcersiceType getType() {
        return type;
    }

    public void setType(ExcersiceType type) {
        this.type = type;
    }

    public ExcersiceValues getValue() {
        return value;
    }

    public void setValue(ExcersiceValues value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ExcersiceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
