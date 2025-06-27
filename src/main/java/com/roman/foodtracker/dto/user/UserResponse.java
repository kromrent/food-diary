
package com.roman.foodtracker.dto.user;

import lombok.Data;
import java.util.Map;

import com.roman.foodtracker.dto.MacroNutrientsDto;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private MacroNutrientsDto macros; 
}