package com.roman.foodtracker.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;    // можно не возвращать, если не нужно
    private String name;
}