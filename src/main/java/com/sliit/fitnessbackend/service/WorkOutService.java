package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.ExcersiceDTO;
import com.sliit.fitnessbackend.dto.WorkOutDTO;
import com.sliit.fitnessbackend.dto.request.WorkoutSaveRequestDTO;

import java.util.List;

public interface WorkOutService {
    public boolean addNewWorkOut(WorkoutSaveRequestDTO workout);
    public boolean addNewExercise(ExcersiceDTO exercise);
    public List<WorkOutDTO> getMyWorkOut();
    public List<WorkOutDTO> getWorkOut();
    public boolean deleteWorkOut(Integer id);
}
