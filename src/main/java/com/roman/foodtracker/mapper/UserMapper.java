package com.roman.foodtracker.mapper;

import com.roman.foodtracker.dto.UserDto;
import com.roman.foodtracker.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        return user;
    }
}