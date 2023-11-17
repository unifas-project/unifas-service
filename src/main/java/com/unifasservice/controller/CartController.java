package com.unifasservice.controller;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddProductToCartRequest;
import com.unifasservice.dto.payload.request.UpdateCartItemRequest;
import com.unifasservice.dto.payload.response.*;
import com.unifasservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;


    @PostMapping("")
    public ResponseEntity<CommonResponse> addToCart(
            @RequestBody AddProductToCartRequest addProduct,
            Authentication authentication) {
        try {
            String username = authentication.getName();

            CommonResponse response = cartService.addToCart(username, addProduct);

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


    @PutMapping("/{cart-item-id}")
    public ResponseEntity<CommonResponse> updateCartItem(
            @PathVariable("cart-item-id") long cartItemId,
            @RequestBody UpdateCartItemRequest updateRequest,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            int newQuantity = updateRequest.getNewQuantity();
            CommonResponse response = cartService.updateCartItem(username, cartItemId, newQuantity);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return (ResponseEntity<CommonResponse>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{cart-item-id}")
    public ResponseEntity<DeleteCartItemResponse> deleteCartItem(
            @PathVariable("cart-item-id") long cartItemId,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            DeleteCartItemResponse response = cartService.deleteCartItem(username, cartItemId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<DeleteCartItemResponse>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);        }
    }

}
