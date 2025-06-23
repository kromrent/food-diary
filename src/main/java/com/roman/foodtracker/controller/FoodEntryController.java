package com.roman.foodtracker.controller;

import com.roman.foodtracker.dto.foodentry.*;
import com.roman.foodtracker.entity.FoodEntry;
import com.roman.foodtracker.entity.Product;
import com.roman.foodtracker.entity.User;
import com.roman.foodtracker.mapper.FoodEntryMapper;
import com.roman.foodtracker.repository.FoodEntryRepository;
import com.roman.foodtracker.repository.ProductRepository;
import com.roman.foodtracker.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/food-entries")
public class FoodEntryController {

    private final FoodEntryRepository entryRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public FoodEntryController(FoodEntryRepository entryRepo, UserRepository userRepo, ProductRepository productRepo) {
        this.entryRepo = entryRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<FoodEntryResponse> getAll() {
        return entryRepo.findAll().stream()
                .map(FoodEntryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FoodEntryResponse create(@RequestBody FoodEntryCreateRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));

        FoodEntry entry = FoodEntryMapper.toEntity(request, user, product);
        return FoodEntryMapper.toResponse(entryRepo.save(entry));
    }

    @PutMapping("/{id}")
    public FoodEntryResponse update(@PathVariable Long id, @RequestBody FoodEntryUpdateRequest request) {
        FoodEntry entry = entryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));

        FoodEntryMapper.updateEntity(entry, request);
        return FoodEntryMapper.toResponse(entryRepo.save(entry));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        entryRepo.deleteById(id);
    }
}
