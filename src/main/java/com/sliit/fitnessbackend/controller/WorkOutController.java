package com.sliit.fitnessbackend.controller;

import com.sliit.fitnessbackend.dto.request.MealPlanSaveRequestDTO;
import com.sliit.fitnessbackend.dto.request.WorkoutSaveRequestDTO;
import com.sliit.fitnessbackend.dto.response.CommonDataResponseDTO;
import com.sliit.fitnessbackend.service.WorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
