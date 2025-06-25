package com.roman.foodtracker.dto.foodentry;
import java.time.LocalDate;
import lombok.Data;

@Data
public class FoodEntryResponse {
    private Long id;
    private String userName;
    private String productName;
    private Double weight;
    private LocalDate date;
}