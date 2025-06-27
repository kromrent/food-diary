package com.roman.foodtracker.repository;

import com.roman.foodtracker.entity.FoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodEntryRepository extends JpaRepository<FoodEntry, Long> {
    List<FoodEntry> findByUserId(Long userId);
}