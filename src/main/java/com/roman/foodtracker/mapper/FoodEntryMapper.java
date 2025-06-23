package com.roman.foodtracker.mapper;

import com.roman.foodtracker.dto.FoodEntryDto;
import com.roman.foodtracker.entity.FoodEntry;
import com.roman.foodtracker.entity.Product;
import com.roman.foodtracker.entity.User;

public class FoodEntryMapper {

    public static FoodEntry toEntity(FoodEntryDto dto, User user, Product product) {
        FoodEntry entry = new FoodEntry();
        entry.setDate(dto.getDate());
        entry.setWeight(dto.getWeight());
        entry.setUser(user);
        entry.setProduct(product);
        return entry;
    }

    public static FoodEntryDto toDto(FoodEntry entry) {
        FoodEntryDto dto = new FoodEntryDto();
        dto.setDate(entry.getDate());
        dto.setWeight(entry.getWeight());
        dto.setUserId(entry.getUser().getId());
        dto.setProductId(entry.getProduct().getId());
        return dto;
    }
}