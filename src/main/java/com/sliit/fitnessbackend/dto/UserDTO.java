package com.sliit.fitnessbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
    private String password;
    private String gender;
    private String role; // USER
    private String visibility; // PUBLIC or PRIVATE
    private String status; // ACTIVE or DELETED
    private String profilePic;
    private boolean isFollowing;
    private boolean isFollower;

    private boolean isMyProfile;

    public UserDTO() {
    }

    public UserDTO(Integer id, String firstName, String lastName, Date dob, String email, String password, String gender, String role,
                   String visibility, String status, String profilePic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.role = role;
        this.visibility = visibility;
        this.status = status;
        this.profilePic = profilePic;
    }

    public UserDTO(Integer id, String firstName, String lastName, Date dob, String email, String password, String gender, String role, String visibility, String status, String profilePic, boolean isFollowing, boolean isFollower, boolean isMyProfile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.visibility = visibility;
        this.status = status;
        this.profilePic = profilePic;
        this.isFollowing = isFollowing;
        this.isFollower = isFollower;
        this.isMyProfile = isMyProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public boolean isFollower() {
        return isFollower;
    }

    public void setFollower(boolean follower) {
        isFollower = follower;
    }

    public boolean isMyProfile() {
        return isMyProfile;
    }

    public void setMyProfile(boolean myProfile) {
        isMyProfile = myProfile;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", visibility='" + visibility + '\'' +
                ", status='" + status + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", isFollowing=" + isFollowing +
                ", isFollower=" + isFollower +
                ", isMyProfile=" + isMyProfile +
                '}';
    }
}
