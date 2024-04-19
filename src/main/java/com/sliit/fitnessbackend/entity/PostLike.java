package com.sliit.fitnessbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "post_like")
public class PostLike {
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

    public PostLike() {
    }

    public PostLike(Integer id, OurUsers user, Post post, Date date) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.date = date;
    }

    public PostLike(OurUsers user, Post post, Date date) {
        this.user = user;
        this.post = post;
        this.date = date;
    }
}
