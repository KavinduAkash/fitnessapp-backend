package com.sliit.fitnessbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "workout_excersice")
public class WorkOutExcersice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "workout", nullable = false)
    private WorkOut workOut;
    @ManyToOne
    @JoinColumn(name = "excersice", nullable = false)
    private Excersice excersice;
    private String value;

    public WorkOutExcersice() {
    }

    public WorkOutExcersice(Integer id, WorkOut workOut, Excersice excersice, String value) {
        this.id = id;
        this.workOut = workOut;
        this.excersice = excersice;
        this.value = value;
    }

    public WorkOutExcersice(WorkOut workOut, Excersice excersice, String value) {
        this.workOut = workOut;
        this.excersice = excersice;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WorkOut getWorkOut() {
        return workOut;
    }

    public void setWorkOut(WorkOut workOut) {
        this.workOut = workOut;
    }

    public Excersice getExcersice() {
        return excersice;
    }

    public void setExcersice(Excersice excersice) {
        this.excersice = excersice;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    @Override
    public String toString() {
        return "WorkOutExcersice{" +
                "id=" + id +
                ", workOut=" + workOut +
                ", excersice=" + excersice +
                ", value='" + value + '\'' +
                '}';
    }
}
