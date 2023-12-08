package com.unifasservice.converter;


import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.dto.payload.response.CartItemResponse;
import com.unifasservice.dto.payload.response.CartItemUpdateResponse;
import com.unifasservice.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemConverter {

    public CartItemResponse fromEntityToDto (CartItem cartItem) {
        CartItemResponse cartProductResponseDto = new CartItemResponse();

        cartProductResponseDto.setId(cartItem.getId());
        cartProductResponseDto.setName(cartItem.getProduct().getName());
        cartProductResponseDto.setProductId(cartItem.getProduct().getId());
        cartProductResponseDto.setColor(cartItem.getVariant().getColor().getName());
        cartProductResponseDto.setSize(cartItem.getVariant().getSize().getName());
        cartProductResponseDto.setPrice(cartItem.getPrice());
        cartProductResponseDto.setQuantity(cartItem.getQuantity());
        cartProductResponseDto.setSubtotal(cartItem.getSubtotal());
        cartProductResponseDto.setImage(null);
        return cartProductResponseDto;
    }

    public List<CartItemResponse> fromListEntityToDto (List<CartItem> cartItemList) {
        List<CartItemResponse> list = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            list.add(fromEntityToDto(cartItem));
        }

        return list;
    }



    public CartItemUpdateResponse UpdateFromEntityToDto (CartItem cartItem) {

        CartItemUpdateResponse cartProductResponseDto = new CartItemUpdateResponse();

        cartProductResponseDto.setId(cartItem.getId());
        cartProductResponseDto.setName(cartItem.getProduct().getName());
        cartProductResponseDto.setColor(cartItem.getVariant().getColor().getName());
        cartProductResponseDto.setSize(cartItem.getVariant().getSize().getName());
        cartProductResponseDto.setPrice(cartItem.getPrice());
        cartProductResponseDto.setQuantity(cartItem.getQuantity());
        cartProductResponseDto.setSubtotal(cartItem.getSubtotal());

        return cartProductResponseDto;
    }

}
