package com.unifasservice.converter;


import com.unifasservice.dto.payload.response.CartItemResponse;
import com.unifasservice.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemConverter {

    public CartItemResponse fromEntityToDto (CartItem cartItem) {
        CartItemResponse cartProductResponseDto = new CartItemResponse();
        cartProductResponseDto.setId(cartItem.getId());
        cartProductResponseDto.setName(cartItem.getProduct().getName());
//        cartProductResponseDto.setColor(cartProduct.getProduct().getVariantList());
//        cartProductResponseDto.setSize();
        cartProductResponseDto.setPrice(cartItem.getPrice());
        cartProductResponseDto.setQuantity(cartItem.getQuantity());
        cartProductResponseDto.setSubtotal(cartItem.getSubtotal());
        return cartProductResponseDto;
    }

    public List<CartItemResponse> fromListEntityToDto (List<CartItem> cartItemList) {
        List<CartItemResponse> list = new ArrayList<>();
        for (CartItem cartProduct : cartItemList) {
            list.add(fromEntityToDto(cartProduct));
        }

        return list;
    }

}
