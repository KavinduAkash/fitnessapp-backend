package com.sliit.fitnessbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sliit.fitnessbackend.enums.WorkOutStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "workout")
public class WorkOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 5000)
    private String title;
    @Column(length = 5000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private OurUsers user;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private boolean isCurrent;
    private WorkOutStatus status;

    public WorkOut() {
    }

    public WorkOut(Integer id, String title, String description, OurUsers user, Date date, boolean isCurrent, WorkOutStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.date = date;
        this.isCurrent = isCurrent;
        this.status = status;
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

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public WorkOutStatus getStatus() {
        return status;
    }

    public void setStatus(WorkOutStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WorkOut{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", date=" + date +
                ", isCurrent=" + isCurrent +
                ", status=" + status +
                '}';
    }
}
