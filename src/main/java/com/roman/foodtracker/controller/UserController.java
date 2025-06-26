package com.roman.foodtracker.controller;

import com.roman.foodtracker.dto.MacroNutrientsDto;
import com.roman.foodtracker.dto.user.UserCreateRequest;
import com.roman.foodtracker.dto.user.UserResponse;
import com.roman.foodtracker.dto.user.UserUpdateRequest;
import com.roman.foodtracker.entity.User;
import com.roman.foodtracker.mapper.UserMapper;
import com.roman.foodtracker.repository.UserRepository;
import com.roman.foodtracker.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
            MacroNutrientsDto macros = userService.calculateMacros(user);
            response.setMacros(macros);
            return response;
        })
        .collect(Collectors.toList());
}



    @PutMapping("/{id}")
        public UserResponse update(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + id));

        UserMapper.updateUser(user, request);
        user = userRepo.save(user);

        MacroNutrientsDto macros = userService.calculateMacros(user);
        UserResponse response = UserMapper.toResponse(user);
        response.setMacros(macros);
        return response;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByEmail(userDetails.getUsername())
                            .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        UserResponse response = UserMapper.toResponse(user);
        response.setMacros(userService.calculateMacros(user));
        return ResponseEntity.ok(response);
    }
}