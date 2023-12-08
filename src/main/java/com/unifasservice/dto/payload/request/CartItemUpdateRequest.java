package com.unifasservice.dto.payload.request;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemUpdateRequest {
    private long id;
    private int updateQuantity;
}
