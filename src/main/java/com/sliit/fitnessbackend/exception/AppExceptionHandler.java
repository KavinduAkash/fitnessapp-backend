package com.sliit.fitnessbackend.exception;

import com.sliit.fitnessbackend.dto.response.ErrorMessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.File;

import static com.sliit.fitnessbackend.constant.ApplicationConstant.*;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleAnyException(Exception ex, WebRequest webRequest) {
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>(
                new ErrorMessageResponseDTO(false, 100, APPLICATION_ERROR_OCCURRED_MESSAGE), HttpStatus.OK);
        return objectResponseEntity;
    }

    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity handleUserException(UserException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMessageResponseDTO(false, ex.getStatus(), ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {FileException.class})
    public ResponseEntity handleFileException(FileException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMessageResponseDTO(false, ex.getStatus(), ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {PostException.class})
    public ResponseEntity handlePostException(PostException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMessageResponseDTO(false, ex.getStatus(), ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {MealException.class})
    public ResponseEntity handleMealException(MealException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMessageResponseDTO(false, ex.getStatus(), ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {WorkOutException.class})
    public ResponseEntity handleWorkOutException(WorkOutException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorMessageResponseDTO(false, ex.getStatus(), ex.getMessage()), HttpStatus.OK);
    }

}
