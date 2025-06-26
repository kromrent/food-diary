package com.roman.foodtracker.dto.user;
import java.time.LocalDate;
import lombok.Data;
import jakarta.validation.constraints.*;
import com.roman.foodtracker.entity.Gender;
import com.roman.foodtracker.entity.ActivityLevel;
@Data
public class UserCreateRequest {
    @NotBlank
    private String name;
    @Min(1)
    @Max(150)
    private int age;
    @Min(50)
    @Max(250)
    private double height; 
    @Max(500)
    private double weight; 
    @NotBlank
    private String password;
    @NotNull
    private Gender gender; 
    @NotBlank
    @Email
    private String email;
    @NotNull
    private ActivityLevel activityLevel;
}