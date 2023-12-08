
package com.unifasservice.service.impl;

import com.unifasservice.converter.CartItemConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.dto.payload.request.CartItemUpdateRequest;
import com.unifasservice.dto.payload.response.*;
import com.unifasservice.entity.*;
import com.unifasservice.repository.*;
import com.unifasservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final VariantRepository variantRepository;

    private final CartItemConverter cartItemConverter;

    private final CartItemRepository cartItemRepository;


    private final SizeRepository sizeRepository;

    private final ColorRepository colorRepository;



 @Override
    public CommonResponse addToCart(CartItemRequest cartItemRequest, long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new IllegalArgumentException("User not found")
            );

            Cart cart = user.getCart();

            Product product = productRepository.findById(cartItemRequest.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + cartItemRequest.getProductId()));


            if (product == null) {
                return createCommonResponse(null, "Product not found", HttpStatus.NOT_FOUND);
            }

            Size foundSize = sizeRepository.findByName(cartItemRequest.getSize());
            Color foundColor = colorRepository.findByName(cartItemRequest.getColor().replace(" active",""));



            Variant foundVariant = variantRepository.findByProductIdAndColorIdAndSizeId(product.getId(), foundColor.getId(), foundSize.getId());
           foundVariant.setColor(foundColor);
           foundVariant.setSize(foundSize);



            CartItem cartItem= cartItemRepository.findByProductIdAndVariantId(product.getId(),foundVariant.getId());

            if (cartItem != null){
                cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
                cartItem.setSubtotal(cartItem.getSubtotal() + cartItemRequest.getQuantity() * product.getPrice());
                cartItemRepository.save(cartItem);
                CartItemResponse cartItemResponse = cartItemConverter.fromEntityToDto(cartItem);
                return createCommonResponse(cartItemResponse,"Product added to cart successfully", HttpStatus.OK);
            }else {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(cartItemRequest.getQuantity());
                cartItem.setVariant(foundVariant);
                cartItem.setCart(cart);
                cartItem.setPrice(product.getPrice());
                cartItem.setSubtotal(product.getPrice()*cartItemRequest.getQuantity());

                cartItemRepository.save(cartItem);
                CartItemResponse cartItemResponse = cartItemConverter.fromEntityToDto(cartItem);
                return createCommonResponse(cartItemResponse,"Product added to cart successfully", HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return createCommonResponse(null, "Failed to add product to cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }


    @Override
    public CommonResponse getCartItems(long userId) {

        CommonResponse commonResponse = new CommonResponse();

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        if (user == null || user.getCart() == null) {

            CommonResponse.builder()
                    .message("User not found or Cart not created ! ")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();
            return commonResponse;
        }

        Cart cart = user.getCart();
//        List<CartItem> cartItemList = cart.getCartItemList();
//
//        List<CartItemResponse> cartItemResponseList = cartItemConverter.fromListEntityToDto(cartItemList);

        CartResponse cartResponse = CartResponse.builder()
                .id(cart.getId())
                .cartItemResponseList(cartItemConverter.fromListEntityToDto(cart.getCartItemList()))
                .build();


        return CommonResponse.builder()
                .message("Show successfully !")
                .statusCode(HttpStatus.OK)
                .data(cartResponse)
                .build();


    }



    @Override
    public CommonResponse updateCartItem( CartItemUpdateRequest updateRequest) {


    CartItem foudCartItem = cartItemRepository.findById(updateRequest.getId())
             .orElseThrow (() -> new IllegalArgumentException("CartItem not found"));

    foudCartItem.setQuantity(updateRequest.getUpdateQuantity());

    Cart foundCart = cartRepository.findById(foudCartItem.getCart().getId())
            .orElseThrow (() -> new IllegalArgumentException("Cart not found"));

    foudCartItem.setCart(foundCart);

    cartItemRepository.save(foudCartItem);

    CartItemUpdateResponse cartItemUpdateResponse = cartItemConverter.UpdateFromEntityToDto(foudCartItem);

        return createCommonResponse(cartItemUpdateResponse,"Product added to cart successfully", HttpStatus.OK);


    }





}

