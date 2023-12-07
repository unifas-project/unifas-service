
package com.unifasservice.service.impl;

import com.unifasservice.converter.CartItemConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddProductToCartRequest;
import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.dto.payload.response.*;
import com.unifasservice.entity.*;
import com.unifasservice.repository.*;
import com.unifasservice.service.CartService;
import com.unifasservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
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
    public CommonResponse addToCart(String username, CartItemRequest cartItemRequest) {
        try {
            User user = userRepository.findByUsername(username);

            if (user == null) {
                return createCommonResponse(null, "User not found", HttpStatus.NOT_FOUND);
            }

            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                user.setCart(cart);
            }

            Product product = productRepository.findById(cartItemRequest.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + cartItemRequest.getProductId()));


            if (product == null) {
                return createCommonResponse(null, "Product not found", HttpStatus.NOT_FOUND);
            }

            Size foundSize = sizeRepository.findByName(cartItemRequest.getSize());
            Color foundColor = colorRepository.findByName(cartItemRequest.getColor());




            Variant foundVariant = variantRepository.findByProductIdAndColorIdAndSizeId(product.getId(), foundSize.getId(), foundColor.getId());
           foundVariant.setColor(foundColor);
           foundVariant.setSize(foundSize);

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setVariant(foundVariant);
            cartItem.setCart(cart);
            cartItem.setPrice(product.getPrice());
            cartItem.setSubtotal(product.getPrice()*cartItemRequest.getQuantity());
            cart.getCartProductList().add(cartItem);
            userRepository.save(user);
            cartItemRepository.save(cartItem);

            CartItemResponse cartItemResponse = cartItemConverter.fromEntityToDto(cartItem);

            return createCommonResponse(cartItemResponse,"Product added to cart successfully", HttpStatus.OK);
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
    public CommonResponse getCartItems(String username) {

        CommonResponse commonResponse = new CommonResponse();

        User user = userRepository.findByUsername(username);
        if (user == null || user.getCart() == null) {

            CommonResponse.builder()
                    .message("User not found or Cart not created ! ")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();
            return commonResponse;
        }

        Cart cart = user.getCart();
        List<CartItem> cartProductList = cart.getCartProductList();

        List<CartItemResponse> cartProductResponseDtoList = cartItemConverter.fromListEntityToDto(cartProductList);


        CommonResponse.builder()
                .message("Show successfully !")
                .statusCode(HttpStatus.OK)
                .data(cartProductResponseDtoList)
                .build();
        return commonResponse;


    }




}

