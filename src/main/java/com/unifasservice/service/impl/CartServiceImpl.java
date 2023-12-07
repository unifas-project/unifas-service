//
//package com.unifasservice.service.impl;
//
//import com.unifasservice.converter.CartItemConverter;
//import com.unifasservice.dto.payload.CommonResponse;
//import com.unifasservice.dto.payload.request.AddProductToCartRequest;
//import com.unifasservice.dto.payload.response.*;
//import com.unifasservice.entity.*;
//import com.unifasservice.repository.*;
//import com.unifasservice.service.CartService;
//import com.unifasservice.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class CartServiceImpl implements CartService {
//
//    private final UserRepository userRepository;
//
//    private final ProductRepository productRepository;
//
//    private final CartRepository cartRepository;
//
//    private final VariantRepository variantRepository;
//
//    private final CartItemConverter cartProductConverter;
//
//    private final CartItemRepository cartItemRepository;
//
//    private final ProductService productService;
//
//
//
//    @Override
//    public CommonResponse addToCart(String username, List<AddProductToCartRequest> cartItems) {
//
//
//        try {
//
//            User user = userRepository.findByUsername(username);
//
//            // Example: Assume you have a CartRepository to manage user's cart
//            Cart userCart = user.getCart();
//
//            // Example: Add each product from cartItems to the user's cart
//            for (AddProductToCartRequest cartItem : cartItems) {
//                Product product = productRepository.findById(cartItem.getProductId());
//                if (product != null) {
//                    // Example: Add the product to the cart with the specified quantity
//                    userCart.addProduct(product, cartItem.getQuantity());
//                }
//            }
//
//
//            cartRepository.save(userCart);
//
//
//            return createCommonResponse("Products added to cart successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            // Handle exceptions appropriately
//            return createCommonResponse(null, "Failed to add products to cart", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//      }
//
//    @Override
//    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
//        return CommonResponse
//                .builder()
//                .data(data)
//                .message(message)
//                .statusCode(statusCode)
//                .build();
//    }
//
//    @Override
//    public CommonResponse getCartItems(String username) {
//
//        CommonResponse commonResponse = new CommonResponse();
//
//        User user = userRepository.findByUsername(username);
//        if (user == null || user.getCart() == null) {
//
//            commonResponse.builder()
//                    .message("User not found or Cart not created ! ")
//                    .statusCode(HttpStatus.BAD_REQUEST)
//                    .data(null)
//                    .build();
//            return commonResponse;
//        }
//
//        Cart cart = user.getCart();
//        List<CartItem> cartProductList = cart.getCartProductList();
//
//        List<CartItemResponse> cartProductResponseDtoList = cartProductConverter.fromListEntityToDto(cartProductList);
//
//
//        commonResponse.builder()
//                .message("Show successfully !")
//                .statusCode(HttpStatus.OK)
//                .data(cartProductResponseDtoList)
//                .build();
//        return commonResponse;
//
//
//    }
//
//
//
//}
//
