package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.WorkOut;
import com.sliit.fitnessbackend.entity.WorkOutExcersice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkoutExcersiceRepo extends JpaRepository<WorkOutExcersice, Integer> {

    @Query("SELECT w FROM WorkOutExcersice w WHERE w.workOut=:workout")
    List<WorkOutExcersice> getByWorkOutId(@Param("workout") WorkOut workout);

    boolean deleteAllByWorkOut(@Param("workout") WorkOut workout);
}
