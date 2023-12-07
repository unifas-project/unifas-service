package com.unifasservice.converter;

import com.unifasservice.dto.payload.request.OrderRequest;
import com.unifasservice.dto.payload.response.OrderLineResponse;
import com.unifasservice.dto.payload.response.OrderResponse;
import com.unifasservice.entity.CartItem;
import com.unifasservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderLineConverter orderLineConverter;
    public Order convertOrderRequestToEntity(OrderRequest orderRequest){
        return Order.builder()
                .date(LocalDateTime.now())
                .totalAmount(orderRequest.getTotalAmount())
                .finalPrice(orderRequest.getFinalPrice())
                .payment(orderRequest.getPayment())
                .build();
    }


    public OrderResponse convertOrderEntityToResponse(Order orderAfterSave) {
        return OrderResponse.builder()
                .id(orderAfterSave.getId())
                .date(orderAfterSave.getDate())
                .totalAmount(orderAfterSave.getTotalAmount())
                .finalPrice(orderAfterSave.getFinalPrice())
                .payment(orderAfterSave.getPayment())
                .orderLineResponseList(orderLineConverter.convertEntityListToDtoListResponse(orderAfterSave.getOrderLineList()))
                .build();
    }

    public OrderLineResponse convertCartItemEntityToOrderLineResponse(CartItem cartItem){
        return OrderLineResponse
                .builder()
                .id(cartItem.getId())
                .productName(cartItem.getProduct().getName())
                .price(cartItem.getProduct().getPrice())
                .quantity(cartItem.getQuantity())
                .subtotal(cartItem.getSubtotal())
                .size(cartItem.getVariant().getSize().getName())
                .color(cartItem.getVariant().getColor().getAcronym())
                .build();
    }

    public List<OrderLineResponse> convertListCartItemEntityToListOrderLineResponse(List<CartItem> cartItemList) {
        return cartItemList.stream().map(this::convertCartItemEntityToOrderLineResponse).collect(Collectors.toList());
    }
}
