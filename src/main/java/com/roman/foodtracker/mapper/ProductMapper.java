package com.roman.foodtracker.mapper;

import com.roman.foodtracker.dto.ProductDto;
import com.roman.foodtracker.entity.Product;

public class ProductMapper {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId()); 
        dto.setName(product.getName());
        dto.setCalories(product.getCalories());
        dto.setProtein(product.getProtein());
        dto.setFat(product.getFat());
        dto.setCarbs(product.getCarbs());
        return dto;
    }

    public static Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setCalories(dto.getCalories());
        product.setProtein(dto.getProtein());
        product.setFat(dto.getFat());
        product.setCarbs(dto.getCarbs());
        return product;
    }
}
