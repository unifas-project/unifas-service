package com.unifasservice.service;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddProductToCartRequest;
import com.unifasservice.dto.payload.response.*;


public interface CartService {
    CommonResponse addToCart(String username, AddProductToCartRequest addProduct);

    CommonResponse getCartItems(String username);

    CommonResponse updateCartItem(String username, long cartProductId, int newQuantity);

    DeleteCartItemResponse deleteCartItem(String username, long cartProductId);
}
