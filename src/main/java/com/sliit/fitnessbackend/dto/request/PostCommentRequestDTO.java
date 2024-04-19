package com.sliit.fitnessbackend.dto.request;

public class PostCommentRequestDTO {
    private Integer postId;
    private String comment;

    public PostCommentRequestDTO() {
    }

    public PostCommentRequestDTO(Integer postId, String comment) {
        this.postId = postId;
        this.comment = comment;
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

    @Override
    public String toString() {
        return "PostCommentRequestDTO{" +
                "postId=" + postId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
