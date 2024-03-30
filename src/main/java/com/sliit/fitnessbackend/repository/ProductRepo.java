package com.sliit.fitnessbackend.repository;

import com.sliit.fitnessbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
