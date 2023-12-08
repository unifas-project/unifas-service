package com.unifasservice.dto.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemRequest {
//    private long id;
    private long productId;
    private String name;
    private String color;
    private String size;
    private int quantity;
    private double price;
    private double subtotal;
}
