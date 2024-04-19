package com.sliit.fitnessbackend.dto;

import java.util.Date;

public class CommentDTO {
    private Integer id;
    private Date date;
    private String comment;
    private UserDTO user;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, Date date, String comment, UserDTO user) {
        this.id = id;
        this.date = date;
        this.comment = comment;
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                '}';
    }
}
