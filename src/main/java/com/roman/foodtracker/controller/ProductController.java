package com.roman.foodtracker.controller;

import com.roman.foodtracker.dto.product.*;
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
    public List<ProductResponse> getAll() {
        return productRepo.findAll().stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductCreateRequest request) {
        Product product = ProductMapper.toEntity(request);
        Product saved = productRepo.save(product);
        return ProductMapper.toResponse(saved);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductUpdateRequest request) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден: " + id));

        ProductMapper.updateProduct(product, request);
        return ProductMapper.toResponse(productRepo.save(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepo.deleteById(id);
    }
}