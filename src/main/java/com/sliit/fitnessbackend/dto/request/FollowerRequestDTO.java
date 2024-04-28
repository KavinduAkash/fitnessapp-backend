package com.sliit.fitnessbackend.dto.request;

public class FollowerRequestDTO {
    private Integer followerId;
    private boolean follower;

    public FollowerRequestDTO() {
    }

    public FollowerRequestDTO(Integer followerId, boolean follower) {
        this.followerId = followerId;
        this.follower = follower;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public boolean isFollower() {
        return follower;
    }

    public void setFollower(boolean follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "FollowerRequestDTO{" +
                "followerId=" + followerId +
                ", follower=" + follower +
                '}';
    }
}
