package com.sliit.fitnessbackend.dto;

import java.util.Date;

public class PostLikeDTO {
    private Integer id;
    private Date date;
    private UserDTO user;

    public PostLikeDTO() {
    }

    public PostLikeDTO(Integer id, Date date, UserDTO user) {
        this.id = id;
        this.date = date;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostLikeDTO{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
