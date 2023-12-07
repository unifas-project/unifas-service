package com.unifasservice.converter;


import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.dto.payload.response.CartItemResponse;
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
        cartProductResponseDto.setColor(cartItem.getVariant().getColor().getName());
        cartProductResponseDto.setSize(cartItem.getVariant().getSize().getName());
        cartProductResponseDto.setPrice(cartItem.getPrice());
        cartProductResponseDto.setQuantity(cartItem.getQuantity());
        cartProductResponseDto.setSubtotal(cartItem.getSubtotal());
        cartProductResponseDto.setImage(cartProductResponseDto.getImage());
        return cartProductResponseDto;
    }

    public List<CartItemResponse> fromListEntityToDto (List<CartItem> cartItemList) {
        List<CartItemResponse> list = new ArrayList<>();
        for (CartItem cartProduct : cartItemList) {
            list.add(fromEntityToDto(cartProduct));
        }

        return list;
    }

    public CartItem convertDTORequestToEntity (CartItemRequest cartItemRequest){
        return CartItem.builder()
                .id(cartItemRequest.getId())
                .build();
    }

    public List<CartItem> convertListDTORequestToListEntity(List<CartItemRequest> cartItemRequestList){
        return cartItemRequestList.stream().map(this::convertDTORequestToEntity).collect(Collectors.toList());
    }

}
