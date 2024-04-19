package com.sliit.fitnessbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "post_comment")
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private OurUsers user;
    @ManyToOne
    @JoinColumn(name = "post", nullable = false)
    private Post post;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(length = 10000)
    private String comment;

    public PostComment() {
    }

    public PostComment(Integer id, OurUsers user, Post post, Date date, String comment) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.date = date;
        this.comment = comment;
    }

    public PostComment(OurUsers user, Post post, Date date, String comment) {
        this.user = user;
        this.post = post;
        this.date = date;
        this.comment = comment;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
