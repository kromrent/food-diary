package com.roman.foodtracker.service;

import com.roman.foodtracker.dto.MacroNutrientsDto;
import com.roman.foodtracker.entity.Gender;
import com.roman.foodtracker.entity.User;
import com.roman.foodtracker.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MacroNutrientsDto calculateMacros(User user) {
       
        double bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge();
        
        if (user.getGender() == Gender.MALE)  {
            bmr = bmr + 5;
        } else {
            bmr = bmr - 161;
        }
        double activityFactor = switch (user.getActivityLevel()) {
            case LOW -> 1.2;
            case MODERATE -> 1.375;
            case ACTIVE -> 1.55;
            case VERY_ACTIVE -> 1.725;
        };
        double totalCalories = bmr * activityFactor;
        
        return new MacroNutrientsDto(
            totalCalories,
            totalCalories * 0.30 / 4,
            totalCalories * 0.30 / 9,
            totalCalories * 0.40 / 4
        );
    }
    public User createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }
}