package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderLineResponse {
    private long id;
    private String productName;
    private double price;
    private int quantity;
    private double subtotal;
    private String size;
    private String color;
}
