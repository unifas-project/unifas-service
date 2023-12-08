package com.unifasservice.service;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.dto.payload.request.CartItemUpdateRequest;
import org.springframework.http.HttpStatus;



public interface CartService {

    CommonResponse getCartItems(long userId);
    CommonResponse addToCart(CartItemRequest cartItems, long userId);
    CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode);
    CommonResponse updateCartItem( CartItemUpdateRequest updateRequest);
}
