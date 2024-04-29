package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    @Query("SELECT u FROM OurUsers u WHERE u.id = :id AND u.status <> 'DELETED'")
    Optional<OurUsers> getUserById(@Param("id") Integer id);
    Optional<OurUsers> findByEmail(String email);
    @Query("SELECT u FROM OurUsers u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<OurUsers> searchUsers(@Param("search") String search);

    @Query("SELECT u FROM OurUsers u WHERE (:search IS NULL OR (LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%'))))")
    List<OurUsers> searchUsers2(@Param("search") String search);
}
