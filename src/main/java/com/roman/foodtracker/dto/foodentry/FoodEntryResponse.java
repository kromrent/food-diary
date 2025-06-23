package com.roman.foodtracker.dto.foodentry;

import lombok.Data;

@Data
public class FoodEntryResponse {
    private Long id;
    private String userName;
    private String productName;
    private Double weight;
}