package com.roman.foodtracker.dto;

import lombok.Data;

@Data
public class FoodEntryDto {
    private Long id;
    private Double weight;
    private String date;
    private Long productId;
    private Long userId;
}