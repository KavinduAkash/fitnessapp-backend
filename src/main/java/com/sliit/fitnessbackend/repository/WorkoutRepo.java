package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.WorkOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutRepo extends JpaRepository<WorkOut, Integer> {
    @Query("SELECT w FROM WorkOut w WHERE w.user=:user")
    List<WorkOut> getWorkoutByUser(@Param("user")OurUsers user);
}
