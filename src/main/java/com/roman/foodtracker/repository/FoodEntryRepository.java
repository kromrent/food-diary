package com.roman.foodtracker.repository;

import com.roman.foodtracker.entity.FoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodEntryRepository extends JpaRepository<FoodEntry, Long> {
}