package com.roman.foodtracker.controller;

import com.roman.foodtracker.dto.user.*;
import com.roman.foodtracker.entity.User;
import com.roman.foodtracker.mapper.UserMapper;
import com.roman.foodtracker.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userRepo.findAll().stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public UserResponse create(@RequestBody UserCreateRequest request) {
        User user = UserMapper.toEntity(request);
        return UserMapper.toResponse(userRepo.save(user));
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + id));
        UserMapper.updateUser(user, request);
        return UserMapper.toResponse(userRepo.save(user));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
}