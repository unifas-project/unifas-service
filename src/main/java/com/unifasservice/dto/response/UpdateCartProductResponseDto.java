package com.unifasservice.dto.response;


import lombok.Data;

@Data
public class UpdateCartProductResponseDto {
    private long id;
    private String name;
    private int quantity;
    private double price;
    private double subtotal;
    private String message;
    private boolean success;
}
