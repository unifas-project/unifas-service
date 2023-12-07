package com.unifasservice.controller;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddProductToCartRequest;
import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.entity.CartItem;
import com.unifasservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;


    @PostMapping("")
    public ResponseEntity<CommonResponse> addToCart(
            @RequestBody CartItemRequest cartItem,
            Authentication authentication) {
        try {
            String username = authentication.getName();

            CommonResponse response = cartService.addToCart(username, cartItem);

            return new ResponseEntity<>(response , HttpStatus.OK);

        } catch (Exception e) {
            CommonResponse errorResponse = new CommonResponse();
            errorResponse.setMessage("Error adding product to cart");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("")
    public ResponseEntity<CommonResponse> getCartItemList(Authentication authentication) {
        try {
            String username = authentication.getName();
            CommonResponse cartItemList = cartService.getCartItems(username);
            return new ResponseEntity<>(cartItemList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(CommonResponse.builder()
                    .message("An error occurred while fetching the cart items.")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
