package com.sliit.fitnessbackend.dto;

import java.util.Date;
import java.util.List;

public class PostDTO {
    private Integer id;
    private Date date;
    private String description;
    private List<PostMediaDTO> images;
    private UserDTO user;
    private List<PostLikeDTO> likes;
    private List<PostCommentDTO> comments;
    private boolean myLike;

    public PostDTO(Integer id, Date date, String description, List<PostMediaDTO> images, UserDTO user, List<PostLikeDTO> likes, List<PostCommentDTO> comments, boolean myLike) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.images = images;
        this.user = user;
        this.likes = likes;
        this.comments = comments;
        this.myLike = myLike;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PostMediaDTO> getImages() {
        return images;
    }

    public void setImages(List<PostMediaDTO> images) {
        this.images = images;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<PostLikeDTO> getLikes() {
        return likes;
    }

    public void setLikes(List<PostLikeDTO> likes) {
        this.likes = likes;
    }

    public List<PostCommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<PostCommentDTO> comments) {
        this.comments = comments;
    }

    public boolean isMyLike() {
        return myLike;
    }

    public void setMyLike(boolean myLike) {
        this.myLike = myLike;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", user=" + user +
                ", likes=" + likes +
                ", comments=" + comments +
                ", myLike=" + myLike +
                '}';
    }
}
