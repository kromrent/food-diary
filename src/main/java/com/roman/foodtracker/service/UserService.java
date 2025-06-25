package com.roman.foodtracker.service;

import com.roman.foodtracker.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    public Map<String, Double> calculateMacros(User user) {
       
        double bmr;
        if (user.getGender().toString().equals("MALE")) {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }
        double activityFactor = switch (user.getActivityLevel()) {
        case LOW -> 1.2;
        case MODERATE -> 1.375;
        case ACTIVE -> 1.55;
        case VERY_ACTIVE -> 1.725;
        };
        double totalCalories = bmr * activityFactor;
        
        Map<String, Double> macros = new HashMap<>();
        macros.put("calories", totalCalories);
        macros.put("protein", totalCalories * 0.30 / 4); 
        macros.put("fat", totalCalories * 0.30 / 9);     
        macros.put("carbs", totalCalories * 0.40 / 4);   
        return macros;
    }
}