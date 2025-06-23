package com.roman.foodtracker.dto.product;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private String name;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
}