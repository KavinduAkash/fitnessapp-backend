package com.sliit.fitnessbackend.dto.request;

public class ResetPasswordRequestDTO {
    private String password;

    public ResetPasswordRequestDTO() {
    }

    public ResetPasswordRequestDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequestDTO{" +
                "password='" + password + '\'' +
                '}';
    }
}
