package com.roman.foodtracker.dto.foodentry;

import lombok.Data;

@Data
public class FoodEntryCreateRequest {
    private Long userId;
    private Long productId;
    private Double weight;
}
