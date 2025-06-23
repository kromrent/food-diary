package com.roman.foodtracker.controller;

import com.roman.foodtracker.dto.UserDto;
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
    public List<UserDto> getAll() {
        return userRepo.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        User user = UserMapper.toEntity(dto);
        User saved = userRepo.save(user);
        return UserMapper.toDto(saved);
    }
    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + id));

        user.setName(dto.getName());
        User updated = userRepo.save(user);
        return UserMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
}