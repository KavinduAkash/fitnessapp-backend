package com.sliit.fitnessbackend.dto;

import java.util.Date;

public class PostCommentDTO {
    private Integer id;
    private Integer postId;
    private String comment;
    private UserDTO user;
    private Date date;

    public PostCommentDTO() {
    }

    public PostCommentDTO(Integer id, Integer postId, String comment, UserDTO user, Date date) {
        this.id = id;
        this.postId = postId;
        this.comment = comment;
        this.user = user;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostCommentDTO{" +
                "id=" + id +
                ", postId=" + postId +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
