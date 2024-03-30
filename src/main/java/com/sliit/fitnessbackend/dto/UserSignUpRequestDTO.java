package com.sliit.fitnessbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.Product;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSignUpRequestDTO {
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
    private String password;
}
