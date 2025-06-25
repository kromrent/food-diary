package com.roman.foodtracker.controller;

import com.roman.foodtracker.dto.user.UserCreateRequest;
import com.roman.foodtracker.dto.user.UserResponse;
import com.roman.foodtracker.dto.user.UserUpdateRequest;
import com.roman.foodtracker.entity.User;
import com.roman.foodtracker.mapper.UserMapper;
import com.roman.foodtracker.repository.UserRepository;
import com.roman.foodtracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepo;
    private final UserService userService;

    public UserController(UserRepository userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userRepo.findAll().stream()
            .map(user -> {
            UserResponse response = UserMapper.toResponse(user);
            Map<String, Double> macros = userService.calculateMacros(user);
            response.setMacros(macros);
            return response;
        })
        .collect(Collectors.toList());
}

    @PostMapping
    public UserResponse create(@RequestBody UserCreateRequest request) {
        User user = UserMapper.toEntity(request);
        user = userRepo.save(user);
        Map<String, Double> macros = userService.calculateMacros(user);
        UserResponse response = UserMapper.toResponse(user);
        response.setMacros(macros);
        return response;
    }

    @PutMapping("/{id}")
        public UserResponse update(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + id));

        UserMapper.updateUser(user, request);
        user = userRepo.save(user);

        Map<String, Double> macros = userService.calculateMacros(user);
        UserResponse response = UserMapper.toResponse(user);
        response.setMacros(macros);
        return response;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
}