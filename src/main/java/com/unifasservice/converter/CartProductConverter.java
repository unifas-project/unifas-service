package com.unifasservice.converter;


import com.unifasservice.dto.response.CartProductResponseDto;
import com.unifasservice.entity.CartProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartProductConverter {

    public CartProductResponseDto fromEntityToDto (CartProduct cartProduct) {
        CartProductResponseDto  cartProductResponseDto = new CartProductResponseDto();
        cartProductResponseDto.setId(cartProduct.getId());
        cartProductResponseDto.setName(cartProduct.getProduct().getName());
//        cartProductResponseDto.setColor(cartProduct.getProduct().getVariantList());
//        cartProductResponseDto.setSize();
        cartProductResponseDto.setPrice(cartProduct.getPrice());
        cartProductResponseDto.setQuantity(cartProduct.getQuantity());
        cartProductResponseDto.setSubtotal(cartProduct.getSubtotal());
        return cartProductResponseDto;
    }

    public List<CartProductResponseDto> fromListEntityToDto (List<CartProduct> cartProductList) {
        List<CartProductResponseDto> cartProductResponseDtoList = new ArrayList<>();
        for (CartProduct cartProduct : cartProductList) {
            cartProductResponseDtoList.add(fromEntityToDto(cartProduct));
        }

        return cartProductResponseDtoList;
    }

}
