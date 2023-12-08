package com.unifasservice.controller;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.dto.payload.request.CartItemUpdateRequest;
import com.unifasservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;


    @PostMapping("/user/{user-id}/cart")
    public ResponseEntity<CommonResponse> addToCart(
            @RequestBody CartItemRequest cartItem, @PathVariable("user-id") long userId) {
        try {

            CommonResponse response = cartService.addToCart(cartItem,userId);

            return new ResponseEntity<>(response , HttpStatus.OK);

        } catch (Exception e) {
            CommonResponse errorResponse = new CommonResponse();
            errorResponse.setMessage("Error adding product to cart");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/cart/{user-id}")
    public ResponseEntity<CommonResponse> getCartItemList(@PathVariable("user-id") long userId) {
        try {
            CommonResponse cartItemList = cartService.getCartItems(userId);
            return new ResponseEntity<>(cartItemList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(CommonResponse.builder()
                    .message("An error occurred while fetching the cart items.")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cart/item/{cart-item-id}")
    public ResponseEntity<CommonResponse> updateCartItem(

            @RequestBody CartItemUpdateRequest updateRequest) {

        try {

            CommonResponse updatedCartItem = cartService.updateCartItem( updateRequest);


            if (updatedCartItem != null && updatedCartItem.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
            } else {

                return new ResponseEntity<>(CommonResponse.builder()
                        .message("Failed to update the cart item.")
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .data(null)
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(CommonResponse.builder()
                    .message("An error occurred while updating the cart item.")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
