package com.unifasservice.dto.request;


import lombok.Data;

@Data
public class AddProductToCartRequestDto {
    private long productId;
    private int quantity;
    private long variantId;

}
