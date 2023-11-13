package com.unifasservice.dto.response;

import lombok.Data;

@Data
public class CartProductResponseDto {
    private long id;
    private String name;
    private String color;
    private String size;
    private int quantity;
    private double price;
    private double subtotal;

}
