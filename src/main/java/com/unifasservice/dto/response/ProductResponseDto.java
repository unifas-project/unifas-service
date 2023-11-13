package com.unifasservice.dto.response;

import lombok.Data;

@Data
public class ProductResponseDto {
    private long id;
    private String name;
    private double price;
    private int stock;
    private String description;
    private int star;
}
