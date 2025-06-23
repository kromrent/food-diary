package com.roman.foodtracker.mapper;

import com.roman.foodtracker.dto.product.*;
import com.roman.foodtracker.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductCreateRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setCalories(request.getCalories());
        product.setProtein(request.getProtein());
        product.setFat(request.getFat());
        product.setCarbs(request.getCarbs());
        return product;
    }

    public static void updateProduct(Product product, ProductUpdateRequest request) {
        product.setName(request.getName());
        product.setCalories(request.getCalories());
        product.setProtein(request.getProtein());
        product.setFat(request.getFat());
        product.setCarbs(request.getCarbs());
    }

    public static ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setCalories(product.getCalories());
        response.setProtein(product.getProtein());
        response.setFat(product.getFat());
        response.setCarbs(product.getCarbs());
        return response;
    }
}