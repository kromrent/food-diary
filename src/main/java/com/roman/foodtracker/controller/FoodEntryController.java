package com.roman.foodtracker.controller;

import com.roman.foodtracker.dto.FoodEntryDto;
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

    private final FoodEntryRepository foodRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public FoodEntryController(FoodEntryRepository foodRepo,
                               UserRepository userRepo,
                               ProductRepository productRepo) {
        this.foodRepo = foodRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<FoodEntryDto> getAll() {
        return foodRepo.findAll().stream()
                .map(FoodEntryMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FoodEntryDto create(@RequestBody FoodEntryDto dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow();
        Product product = productRepo.findById(dto.getProductId()).orElseThrow();

        FoodEntry entry = FoodEntryMapper.toEntity(dto, user, product);
        FoodEntry saved = foodRepo.save(entry);

        return FoodEntryMapper.toDto(saved);
    }
}