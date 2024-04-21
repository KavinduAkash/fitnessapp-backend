package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.WorkOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepo extends JpaRepository<WorkOut, Integer> {
}
