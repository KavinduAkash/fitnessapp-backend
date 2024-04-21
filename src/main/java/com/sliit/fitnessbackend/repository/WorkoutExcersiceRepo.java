package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.WorkOut;
import com.sliit.fitnessbackend.entity.WorkOutExcersice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutExcersiceRepo extends JpaRepository<WorkOutExcersice, Integer> {
}
