package com.sliit.fitnessbackend.service.impl;

import com.sliit.fitnessbackend.dto.ExcersiceDTO;
import com.sliit.fitnessbackend.dto.WorkOutDTO;
import com.sliit.fitnessbackend.dto.WorkOutExcersiceDTO;
import com.sliit.fitnessbackend.dto.request.WorkoutSaveRequestDTO;
import com.sliit.fitnessbackend.entity.Excersice;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.WorkOut;
import com.sliit.fitnessbackend.entity.WorkOutExcersice;
import com.sliit.fitnessbackend.enums.ExcersiceType;
import com.sliit.fitnessbackend.enums.ExcersiceValues;
import com.sliit.fitnessbackend.enums.WorkOutStatus;
import com.sliit.fitnessbackend.exception.UserException;
import com.sliit.fitnessbackend.exception.WorkOutException;
import com.sliit.fitnessbackend.repository.ExcersiceRepo;
import com.sliit.fitnessbackend.repository.OurUserRepo;
import com.sliit.fitnessbackend.repository.WorkoutExcersiceRepo;
import com.sliit.fitnessbackend.repository.WorkoutRepo;
import com.sliit.fitnessbackend.service.WorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WorkOutServiceImpl implements WorkOutService {

    @Autowired
    public OurUserRepo ourUserRepo;

    @Autowired
    public WorkoutRepo workoutRepo;

    @Autowired
    public ExcersiceRepo excersiceRepo;

    @Autowired
    public WorkoutExcersiceRepo workoutExcersiceRepo;

    @Override
    public boolean addNewWorkOut(WorkoutSaveRequestDTO workout) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // get user
            OurUsers ourUsers = byEmail.get();

            // WorkOut(String title, String description, OurUsers user, Date date, boolean isCurrent, WorkOutStatus status)
            WorkOut save = workoutRepo.save(new WorkOut(workout.getTitle(), workout.getDescription(), ourUsers, new Date(), workout.isCurrent(), WorkOutStatus.ACTIVE));

            for (WorkOutExcersiceDTO exercise : workout.getExercises()) {
                // WorkOutExcersice(WorkOut workOut, Excersice excersice, String value)
                Optional<Excersice> byId = excersiceRepo.findById(exercise.getExcersiceId());
                if(byId.isEmpty())
                    throw new WorkOutException(404, "Exercise not found");
                // WorkOutExcersice(WorkOut workOut, Excersice excersice, String value)
                workoutExcersiceRepo.save(new WorkOutExcersice(save, byId.get(), exercise.getValue()));
            }

            return true;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean addNewExercise(ExcersiceDTO exercise) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // Excersice(String name, ExcersiceType type, ExcersiceValues value)
            excersiceRepo.save(new Excersice(exercise.getName(), exercise.getType(), exercise.getValue()));

            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<WorkOutDTO> getMyWorkOut() {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // get user
            OurUsers ourUsers = byEmail.get();

            List<WorkOutDTO> workOutDTOS = new ArrayList<>();
            List<WorkOut> workoutByUser = workoutRepo.getWorkoutByUser(ourUsers);
            for (WorkOut w : workoutByUser) {
                // WorkOutDTO(Integer id, String title, String description, boolean isCurrent, List<WorkOutExcersiceDTO> exercises)
                workOutDTOS.add(new WorkOutDTO(w.getId(), w.getTitle(), w.getDescription(), w.isCurrent(), new ArrayList<>()));
            }
            return  workOutDTOS;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<WorkOutDTO> getWorkOut() {
        try {
            List<WorkOutDTO> workOutDTOS = new ArrayList<>();
            List<WorkOut> workoutByUser = workoutRepo.findAll();
            for (WorkOut w : workoutByUser) {
                // WorkOutDTO(Integer id, String title, String description, boolean isCurrent, List<WorkOutExcersiceDTO> exercises)
                workOutDTOS.add(new WorkOutDTO(w.getId(), w.getTitle(), w.getDescription(), w.isCurrent(), new ArrayList<>()));
            }
            return  workOutDTOS;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteWorkOut(Integer id) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // get user
            OurUsers ourUsers = byEmail.get();

            Optional<WorkOut> byId = workoutRepo.findById(id);
            if(byId.isEmpty()) throw new WorkOutException(404, "Workout not found");

            WorkOut workOut = byId.get();
            workOut.setStatus(WorkOutStatus.DELETED);

            workoutRepo.save(workOut);

            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
