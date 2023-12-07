package com.unifasservice.service;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddProductToCartRequest;
import com.unifasservice.dto.payload.response.*;
import com.unifasservice.entity.CartItem;
import org.springframework.http.HttpStatus;

import java.util.List;


public interface CartService {

    CommonResponse getCartItems(String username);
    CommonResponse addToCart(String username, List<AddProductToCartRequest> cartItems);
    CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode);
}
