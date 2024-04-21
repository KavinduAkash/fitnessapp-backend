package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.ExcersiceDTO;
import com.sliit.fitnessbackend.dto.WorkOutDTO;
import com.sliit.fitnessbackend.dto.request.MealPlanSaveRequestDTO;
import com.sliit.fitnessbackend.dto.request.WorkoutSaveRequestDTO;
import com.sliit.fitnessbackend.dto.response.CommonDataResponseDTO;
import com.sliit.fitnessbackend.service.WorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/workout")
public class WorkOutController {

    @Autowired
    public WorkOutService workOutService;
    @PostMapping
    public ResponseEntity createWork(@RequestBody WorkoutSaveRequestDTO workout) {
        boolean save = workOutService.addNewWorkOut(workout);
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "Workout plan created successfully!", ""
                ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createExercise(@RequestBody ExcersiceDTO exercise) {
        boolean save = workOutService.addNewExercise(exercise);
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "Exercise created successfully!", ""
                ), HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity getMyWorkOut() {
        List<WorkOutDTO> save = workOutService.getMyWorkOut();
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "", save
                ), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getWorkOut() {
        List<WorkOutDTO> save = workOutService.getWorkOut();
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "", save
                ), HttpStatus.OK);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity getWorkOut(@PathVariable Integer workoutId) {
        boolean b = workOutService.deleteWorkOut(workoutId);
        return new ResponseEntity<>(
                new CommonDataResponseDTO<>(true, "Workout deleted successfully!", ""
                ), HttpStatus.OK);
    }
}
