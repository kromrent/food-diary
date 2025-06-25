package com.roman.foodtracker.dto.user;

import com.roman.foodtracker.entity.Gender;
import com.roman.foodtracker.entity.ActivityLevel;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UserUpdateRequest {
    @NotBlank

    @NotBlank
    private String name;

    @Min(1)
    @Max(150)
    private int age;

    @Min(50)
    @Max(250)
    private double height;

    @Min(20)
    @Max(500)
    private double weight;

;

    @NotNull
    private Gender gender;

    @NotNull
    private ActivityLevel activityLevel;
}