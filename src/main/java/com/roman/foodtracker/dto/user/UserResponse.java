// src/main/java/com/roman/foodtracker/dto/user/UserResponse.java
package com.roman.foodtracker.dto.user;

import lombok.Data;
import java.util.Map;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private Map<String, Double> macros; 
}