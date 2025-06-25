package com.roman.foodtracker.dto.foodentry;
import java.time.LocalDate;
import lombok.Data;

@Data
public class FoodEntryUpdateRequest {
    private Double weight;
    private LocalDate date;
}