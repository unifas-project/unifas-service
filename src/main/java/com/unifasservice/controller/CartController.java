package com.unifasservice.controller;


import com.unifasservice.dto.request.AddProductToCartRequestDto;
import com.unifasservice.dto.request.UpdateCartProductRequestDto;
import com.unifasservice.dto.response.AddProductToCartResponseDto;
import com.unifasservice.dto.response.CartProductResponseDto;
import com.unifasservice.dto.response.DeleteCartProductResponseDto;
import com.unifasservice.dto.response.UpdateCartProductResponseDto;
import com.unifasservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-product")
    public ResponseEntity<AddProductToCartResponseDto> addToCart(
            @RequestBody AddProductToCartRequestDto addProduct,
            Authentication authentication) {
        try {
            String username = authentication.getName();

            AddProductToCartResponseDto responseDto = cartService.addToCart(username, addProduct);

            return new ResponseEntity<>(responseDto , HttpStatus.OK);

        } catch (Exception e) {
            AddProductToCartResponseDto errorResponse = new AddProductToCartResponseDto();
            errorResponse.setMessage("Error adding product to cart");
            errorResponse.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/cart-products")
    public ResponseEntity<List<CartProductResponseDto>> getCartProducts(Authentication authentication) {
        try {
            String username = authentication.getName();
            List<CartProductResponseDto> cartProducts = cartService.getCartProducts(username);
            return new ResponseEntity<>(cartProducts , HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


    @PutMapping("/{cartProductId}")
    public ResponseEntity<UpdateCartProductResponseDto> updateCartProduct(
            @PathVariable("cartProductId") long cartProductId,
            @RequestBody UpdateCartProductRequestDto updateRequest,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            int newQuantity = updateRequest.getNewQuantity();
            UpdateCartProductResponseDto responseDto = cartService.updateCartProduct(username, cartProductId, newQuantity);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<UpdateCartProductResponseDto>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{cartProductId}")
    public ResponseEntity<DeleteCartProductResponseDto> deleteCartProduct(
            @PathVariable("cartProductId") long cartProductId,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            DeleteCartProductResponseDto responseDto = cartService.deleteCartProduct(username, cartProductId);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<DeleteCartProductResponseDto>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);        }
    }


}
