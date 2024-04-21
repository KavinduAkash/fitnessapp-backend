package com.sliit.fitnessbackend.service;

import com.sliit.fitnessbackend.dto.WorkOutDTO;
import com.sliit.fitnessbackend.dto.request.WorkoutSaveRequestDTO;

import java.util.List;

public interface WorkOutService {
    public boolean addNewWorkOut(WorkoutSaveRequestDTO workout);
    public List<WorkOutDTO> getMyWorkOut();
    public List<WorkOutDTO> getWorkOut();
}
