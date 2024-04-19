package com.sliit.fitnessbackend.dto;

import java.util.Date;
import java.util.List;

public class PostDTO {
    private Integer id;
    private Date date;
    private String description;
    private List<PostMediaDTO> images;
    private UserDTO user;


    public PostDTO(Integer id, Date date, String description, List<PostMediaDTO> images, UserDTO user) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.images = images;
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

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", user=" + user +
                '}';
    }
}
