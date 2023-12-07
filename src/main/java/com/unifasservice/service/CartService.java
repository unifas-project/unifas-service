package com.unifasservice.service;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.CartItemRequest;
import org.springframework.http.HttpStatus;



public interface CartService {

    CommonResponse getCartItems(String username);
    CommonResponse addToCart(String username, CartItemRequest cartItems);
    CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode);
}
