package com.sliit.fitnessbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "follower")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private OurUsers user;
    @ManyToOne
    @JoinColumn(name = "follower", nullable = false)
    private OurUsers follower;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Follower() {
    }

    public Follower(OurUsers user, OurUsers follower, Date date) {
        this.user = user;
        this.follower = follower;
        this.date = date;
    }

    public Follower(Integer id, OurUsers user, OurUsers follower, Date date) {
        this.id = id;
        this.user = user;
        this.follower = follower;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id=" + id +
                ", user=" + user +
                ", follower=" + follower +
                ", date=" + date +
                '}';
    }
}
