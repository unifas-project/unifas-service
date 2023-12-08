package com.unifasservice.dto.payload.response;

import com.unifasservice.entity.CartItem;
import com.unifasservice.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CartResponse {
    private long id;
    private List<CartItemResponse> cartItemResponseList;

}
