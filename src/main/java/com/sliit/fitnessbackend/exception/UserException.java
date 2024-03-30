package com.sliit.fitnessbackend.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserException extends RuntimeException {
    private int status;
    private String message;
    public UserException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }


}
