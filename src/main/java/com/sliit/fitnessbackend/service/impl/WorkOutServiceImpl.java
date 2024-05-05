package com.sliit.fitnessbackend.service.impl;

import com.sliit.fitnessbackend.dto.ExcersiceDTO;
import com.sliit.fitnessbackend.dto.UserDTO;
import com.sliit.fitnessbackend.dto.WorkOutDTO;
import com.sliit.fitnessbackend.dto.request.WorkoutSaveRequestDTO;
import com.sliit.fitnessbackend.entity.Excersice;
import com.sliit.fitnessbackend.entity.OurUsers;
import com.sliit.fitnessbackend.entity.WorkOut;
import com.sliit.fitnessbackend.entity.WorkOutExcersice;
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

            for (ExcersiceDTO exercise : workout.getExercises()) {
                // WorkOutExcersice(WorkOut workOut, Excersice excersice, String value)
                Excersice ex = null;
                if(exercise.getId().equals(0)) {
                    ex = excersiceRepo.save(new Excersice(exercise.getName(), exercise.getType(), exercise.getValue()));
                } else {
                    Optional<Excersice> byId = excersiceRepo.findById(exercise.getId());
                    if(byId.isEmpty())
                        throw new WorkOutException(404, "Exercise not found");

                    ex = byId.get();
                }
                // WorkOutExcersice(WorkOut workOut, Excersice excersice, String value)
                workoutExcersiceRepo.save(new WorkOutExcersice(save, ex, exercise.getDesc()));
            }

            return true;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateWorkOut(WorkoutSaveRequestDTO workout) {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            // get user
            OurUsers ourUsers = byEmail.get();

            Optional<WorkOut> byId1 = workoutRepo.findById(workout.getId());

            if(byId1.isEmpty()) throw new UserException(404, "Workout not found");

            WorkOut workOutEntity = byId1.get();
            workOutEntity.setTitle(workout.getTitle());
            workOutEntity.setDescription(workout.getDescription());

            workoutExcersiceRepo.deleteAllByWorkOut(workOutEntity);

            for (ExcersiceDTO exercise : workout.getExercises()) {
                // WorkOutExcersice(WorkOut workOut, Excersice excersice, String value)
                Excersice ex = null;
                if(exercise.getId().equals(0)) {
                    ex = excersiceRepo.save(new Excersice(exercise.getName(), exercise.getType(), exercise.getValue()));
                } else {
                    Optional<Excersice> byId = excersiceRepo.findById(exercise.getId());
                    if(byId.isEmpty())
                        throw new WorkOutException(404, "Exercise not found");

                    ex = byId.get();
                }
                // WorkOutExcersice(WorkOut workOut, Excersice excersice, String value)
                workoutExcersiceRepo.save(new WorkOutExcersice(workOutEntity, ex, exercise.getDesc()));
            }

            workoutRepo.save(workOutEntity);

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
    public List<ExcersiceDTO> getAllExercise() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            List<ExcersiceDTO> allEx = new ArrayList<>();
            List<Excersice> all = excersiceRepo.findAll();
            for (Excersice ex : all) {
                allEx.add(new ExcersiceDTO(ex.getId(), ex.getName(), ex.getType(), ex.getValue(), ""));
            }
            return allEx;
        }catch (Exception e) {
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

            List<WorkOutDTO> workOutDTOS = new ArrayList<>();
            List<WorkOut> workoutByUser = workoutRepo.getWorkoutByUser(byEmail.get());
            for (WorkOut w : workoutByUser) {
                OurUsers user = w.getUser();

                UserDTO userDTO = new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        user.getVisibility(),
                        user.getStatus(),
                        user.getProfilePic()
                );
                List<WorkOutExcersice> byWorkOutId = workoutExcersiceRepo.getByWorkOutId(w);
                List<ExcersiceDTO> excersiceDTOS =  new ArrayList<>();
                for (WorkOutExcersice we : byWorkOutId) {
//                       public ExcersiceDTO(Integer id, String name, ExcersiceType type, ExcersiceValues value, String desc)
                    excersiceDTOS.add(new ExcersiceDTO(we.getId(), we.getExcersice().getName(), we.getExcersice().getType(), ExcersiceValues.NOTE, we.getValue()));
                }
                // WorkOutDTO(Integer id, String title, String description, boolean isCurrent, List<WorkOutExcersiceDTO> exercises)
                workOutDTOS.add(new WorkOutDTO(w.getId(), w.getTitle(), w.getDescription(), w.isCurrent(), excersiceDTOS, userDTO));
            }
            return  workOutDTOS;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<WorkOutDTO> getWorkOut() {
        try {
            // identify user via token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<OurUsers> byEmail = ourUserRepo.findByEmail(authentication.getName());
            if (byEmail.isEmpty()) throw new UserException(401, "Unauthorized action");

            List<WorkOutDTO> workOutDTOS = new ArrayList<>();
            List<WorkOut> workoutByUser = workoutRepo.findAll();
            for (WorkOut w : workoutByUser) {
                OurUsers user = w.getUser();

                UserDTO userDTO = new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        user.getVisibility(),
                        user.getStatus(),
                        user.getProfilePic()
                );
                List<WorkOutExcersice> byWorkOutId = workoutExcersiceRepo.getByWorkOutId(w);
                List<ExcersiceDTO> excersiceDTOS =  new ArrayList<>();
                for (WorkOutExcersice we : byWorkOutId) {
//                       public ExcersiceDTO(Integer id, String name, ExcersiceType type, ExcersiceValues value, String desc)
                    excersiceDTOS.add(new ExcersiceDTO(we.getId(), we.getExcersice().getName(), we.getExcersice().getType(), ExcersiceValues.NOTE, we.getValue()));
                }
                // WorkOutDTO(Integer id, String title, String description, boolean isCurrent, List<WorkOutExcersiceDTO> exercises)
                workOutDTOS.add(new WorkOutDTO(w.getId(), w.getTitle(), w.getDescription(), w.isCurrent(), excersiceDTOS, userDTO));
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

            List<WorkOutExcersice> byWorkOutId = workoutExcersiceRepo.getByWorkOutId(workOut);
            workoutExcersiceRepo.deleteAll(byWorkOutId);
            workoutRepo.delete(workOut);

            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
