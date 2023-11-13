package com.unifasservice.service;


import com.unifasservice.dto.request.AddProductToCartRequestDto;
import com.unifasservice.dto.response.AddProductToCartResponseDto;
import com.unifasservice.dto.response.CartProductResponseDto;
import com.unifasservice.dto.response.DeleteCartProductResponseDto;
import com.unifasservice.dto.response.UpdateCartProductResponseDto;
import com.unifasservice.entity.CartProduct;

import java.util.List;

public interface CartService {
    AddProductToCartResponseDto addToCart(String username, AddProductToCartRequestDto addProduct);

    List<CartProductResponseDto> getCartProducts(String username);

    UpdateCartProductResponseDto updateCartProduct(String username, long cartProductId, int newQuantity);

    DeleteCartProductResponseDto deleteCartProduct(String username, long cartProductId);
}
