package com.roman.foodtracker.mapper;

import com.roman.foodtracker.dto.user.*;
import com.roman.foodtracker.entity.User;

public class UserMapper {

    public static User toEntity(UserCreateRequest request) {
        User user = new User();
        user.setName(request.getName());
        return user;
    }

    public static void updateUser(User user, UserUpdateRequest request) {
        user.setName(request.getName());
    }

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        return response;
    }
}