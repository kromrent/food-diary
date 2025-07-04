package com.roman.foodtracker.mapper;

import com.roman.foodtracker.dto.foodentry.*;
import com.roman.foodtracker.entity.FoodEntry;
import com.roman.foodtracker.entity.Product;
import com.roman.foodtracker.entity.User;

public class FoodEntryMapper {

    public static FoodEntry toEntity(FoodEntryCreateRequest request, User user, Product product) {
        FoodEntry entry = new FoodEntry();
        entry.setDate(request.getDate());
        entry.setUser(user);
        entry.setProduct(product);
        entry.setWeight(request.getWeight());  
        return entry;
    }

    public static void updateEntity(FoodEntry entry, FoodEntryUpdateRequest request) {
        entry.setWeight(request.getWeight()); 
    }

    public static FoodEntryResponse toResponse(FoodEntry entry) {
        FoodEntryResponse response = new FoodEntryResponse();
        response.setDate(entry.getDate());
        response.setId(entry.getId());
        response.setUserName(entry.getUser().getName());
        response.setProductName(entry.getProduct().getName());
        response.setWeight(entry.getWeight());
        return response;
    }
}
