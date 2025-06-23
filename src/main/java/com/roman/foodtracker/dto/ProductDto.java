package com.roman.foodtracker.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
}