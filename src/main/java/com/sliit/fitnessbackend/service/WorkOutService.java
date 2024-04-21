package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.request.WorkoutSaveRequestDTO;

public interface WorkOutService {
    public boolean addNewWorkOut(WorkoutSaveRequestDTO workout);
}
