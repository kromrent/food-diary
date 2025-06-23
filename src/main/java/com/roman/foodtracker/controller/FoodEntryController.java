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
@RequestMapping("/api/entries")
public class FoodEntryController {

    private final FoodEntryRepository entryRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public FoodEntryController(FoodEntryRepository entryRepo,
                               ProductRepository productRepo,
                               UserRepository userRepo) {
        this.entryRepo = entryRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<FoodEntryDto> getAll() {
        return entryRepo.findAll()
                .stream()
                .map(FoodEntryMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FoodEntryDto create(@RequestBody FoodEntryDto dto) {
        FoodEntry entry = new FoodEntry();
        entry.setDate(dto.getDate());
        entry.setWeight(dto.getWeight());

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден: " + dto.getProductId()));
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + dto.getUserId()));

        entry.setProduct(product);
        entry.setUser(user);

        return FoodEntryMapper.toDto(entryRepo.save(entry));
    }

    @PutMapping("/{id}")
    public FoodEntryDto update(@PathVariable Long id, @RequestBody FoodEntryDto dto) {
        FoodEntry entry = entryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись не найдена: " + id));

        entry.setDate(dto.getDate());
        entry.setWeight(dto.getWeight());

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден: " + dto.getProductId()));
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + dto.getUserId()));

        entry.setProduct(product);
        entry.setUser(user);

        return FoodEntryMapper.toDto(entryRepo.save(entry));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        entryRepo.deleteById(id);
    }
}
