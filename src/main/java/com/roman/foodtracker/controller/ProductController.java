package com.roman.foodtracker.controller;

import com.roman.foodtracker.dto.ProductDto;
import com.roman.foodtracker.entity.Product;
import com.roman.foodtracker.mapper.ProductMapper;
import com.roman.foodtracker.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<ProductDto> getAll() {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto dto) {
        Product product = ProductMapper.toEntity(dto);
        Product saved = productRepo.save(product);
        return ProductMapper.toDto(saved);
    }
}
