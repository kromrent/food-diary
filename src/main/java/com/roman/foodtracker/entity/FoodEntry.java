package com.roman.foodtracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "food_entries")
@Data
public class FoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight; // в граммах
    private String date;   // YYYY-MM-DD (можно заменить на LocalDate)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}