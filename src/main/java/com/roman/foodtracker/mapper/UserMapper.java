package com.roman.foodtracker.mapper;

import com.roman.foodtracker.dto.user.*;
import com.roman.foodtracker.entity.User;

public class UserMapper {

    public static User toEntity(UserCreateRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setHeight(request.getHeight());
        user.setWeight(request.getWeight());
        user.setPassword(request.getPassword());
        user.setGender(request.getGender());
        user.setActivityLevel(request.getActivityLevel());
        return user;
    }

    public static void updateUser(User user, UserUpdateRequest request) {
    user.setName(request.getName());
    user.setAge(request.getAge());
    user.setHeight(request.getHeight());
    user.setWeight(request.getWeight());
    user.setGender(request.getGender());
    user.setActivityLevel(request.getActivityLevel());
}


    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        return response;
    }
}
