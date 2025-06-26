package com.roman.foodtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MacroNutrientsDto {
    private double calories;
    private double protein;
    private double fat;
    private double carbs;

}
