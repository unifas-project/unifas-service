package com.unifasservice.dto.payload.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemUpdateResponse {

    private long id;
    private String image;
    private String name;
    private String color;
    private String size;
    private int quantity;
    private double price;
    private double subtotal;


}