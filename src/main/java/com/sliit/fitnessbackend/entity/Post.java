package com.sliit.fitnessbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sliit.fitnessbackend.enums.PostStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private OurUsers user;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(length = 5000)
    private String description;
    private PostStatus status;

    public Post() {
    }

    public Post(OurUsers user, Date date, String description, PostStatus status) {
        this.user = user;
        this.date = date;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }


}
