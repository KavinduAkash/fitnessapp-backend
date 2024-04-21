package com.sliit.fitnessbackend.entity;

import com.sliit.fitnessbackend.enums.ExcersiceType;
import com.sliit.fitnessbackend.enums.ExcersiceValues;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "excersice")
public class Excersice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 5000)
    private String name;
    private ExcersiceType type;
    private ExcersiceValues value;

    public Excersice() {
    }

    public Excersice(Integer id, String name, ExcersiceType type, ExcersiceValues value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Excersice(String name, ExcersiceType type, ExcersiceValues value) {
        this.name = name;
        this.type = type;
        this.value = value;
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

    @Override
    public String toString() {
        return "Excersice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
