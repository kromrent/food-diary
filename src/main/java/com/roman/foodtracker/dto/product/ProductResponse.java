package com.roman.foodtracker.dto.product;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
}