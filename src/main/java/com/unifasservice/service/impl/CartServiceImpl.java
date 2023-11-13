package com.unifasservice.service.impl;

import com.unifasservice.converter.CartProductConverter;
import com.unifasservice.dto.request.AddProductToCartRequestDto;
import com.unifasservice.dto.response.AddProductToCartResponseDto;
import com.unifasservice.dto.response.CartProductResponseDto;
import com.unifasservice.dto.response.DeleteCartProductResponseDto;
import com.unifasservice.dto.response.UpdateCartProductResponseDto;
import com.unifasservice.entity.*;
import com.unifasservice.repository.*;
import com.unifasservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private VariantRepository variantRepository;
    @Autowired
    private CartProductConverter cartProductConverter;
    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public AddProductToCartResponseDto addToCart(String username, AddProductToCartRequestDto addProduct) {

        AddProductToCartResponseDto responseDto = new AddProductToCartResponseDto();


        User user = userRepository.findByUsername(username);
        if (user == null) {
            responseDto.setMessage("User not found");
            responseDto.setSuccess(false);
            return responseDto;
        }


        long productId = addProduct.getProductId();
        Product product = productRepository.findById(productId)
                .orElse(null);

        if (product == null) {
            responseDto.setMessage("Product not found");
            responseDto.setSuccess(false);
            return responseDto;
        }

        long variantId = addProduct.getVariantId();
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        if (product.getVariantList() == null) {
            product.setVariantList(new ArrayList<>());
        }

        variant.setProduct(product);
        product.getVariantList().add(variant);

        productRepository.save(product);


        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        cartRepository.save(cart);


        responseDto.setId(product.getId());
        responseDto.setQuantity(addProduct.getQuantity());
        responseDto.setPrice(product.getPrice());
        responseDto.setSubtotal(addProduct.getQuantity() * product.getPrice());
        responseDto.setMessage("Product added to cart successfully");
        responseDto.setSuccess(true);

        return responseDto;

      }

    @Override
    public List<CartProductResponseDto> getCartProducts(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null || user.getCart() == null) {
            return Collections.emptyList();
        }

        Cart cart = user.getCart();
        List<CartProduct> cartProductList = cart.getCartProductList();

        List<CartProductResponseDto> cartProductResponseDtoList = cartProductConverter.fromListEntityToDto(cartProductList);


        return cartProductResponseDtoList;
    }

    @Override
    public UpdateCartProductResponseDto updateCartProduct(String username, long cartProductId, int newQuantity) {
        UpdateCartProductResponseDto responseDto = new UpdateCartProductResponseDto();

        User user = userRepository.findByUsername(username);
        if (user == null || user.getCart() == null) {
            responseDto.setMessage("User or cart not found");
            responseDto.setSuccess(false);
            return responseDto;
        }

        Cart cart = user.getCart();


        for (CartProduct cartProduct : cart.getCartProductList()) {
            if (cartProduct.getId() == cartProductId) {

                cartProduct.setQuantity(newQuantity);
                cartProduct.setSubtotal(newQuantity*cartProduct.getPrice());
                cartProductRepository.save(cartProduct);

                responseDto.setId(cartProduct.getId());
                responseDto.setName(cartProduct.getProduct().getName());
                responseDto.setPrice(cartProduct.getPrice());
                responseDto.setQuantity(cartProduct.getQuantity());
                responseDto.setSubtotal(cartProduct.getSubtotal());
                responseDto.setMessage("CartProduct updated successfully");
                responseDto.setSuccess(true);
                return responseDto;
            }
        }


        responseDto.setMessage("CartProduct not found");
        responseDto.setSuccess(false);
        return responseDto;
    }

    @Override
    public DeleteCartProductResponseDto deleteCartProduct(String username, long cartProductId) {
        DeleteCartProductResponseDto responseDto = new DeleteCartProductResponseDto();

        User user = userRepository.findByUsername(username);
        if (user == null || user.getCart() == null) {
            responseDto.setMessage("User or cart not found");
            responseDto.setSuccess(false);
            return responseDto;
        }

        Cart cart = user.getCart();


        for (CartProduct cartProduct : cart.getCartProductList()) {
            if (cartProduct.getId() == cartProductId) {

                cart.getCartProductList().remove(cartProduct);
                cartRepository.save(cart);
                responseDto.setMessage("CartProduct deleted successfully");
                responseDto.setSuccess(true);
                return responseDto;
            }
        }


        responseDto.setMessage("CartProduct not found");
        responseDto.setSuccess(false);
        return responseDto;
    }


}
