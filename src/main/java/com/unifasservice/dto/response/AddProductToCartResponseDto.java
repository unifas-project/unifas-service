package com.unifasservice.dto.response;


import lombok.Data;

@Data
public class AddProductToCartResponseDto {
    private long id;
    private int quantity;
    private double price;
    private double subtotal;
    private String message;
    private boolean success;

}
