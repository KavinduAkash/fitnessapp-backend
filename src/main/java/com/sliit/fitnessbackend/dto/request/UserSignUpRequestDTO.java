package com.sliit.fitnessbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSignUpRequestDTO {
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender; // MALE or FEMALE or OTHER
    private String email;
    private String password;
}
