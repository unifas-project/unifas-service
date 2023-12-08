package com.unifasservice.service.impl;

import com.unifasservice.converter.CartItemConverter;
import com.unifasservice.converter.OrderConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.CartItemRequest;
import com.unifasservice.dto.payload.request.OrderRequest;
import com.unifasservice.dto.payload.response.OrderLineResponse;
import com.unifasservice.dto.payload.response.OrderResponse;
import com.unifasservice.entity.*;
import com.unifasservice.repository.*;
import com.unifasservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;

    private final OrderConverter orderConverter;
    private final CartItemConverter cartItemConverter;

    @Override
    public CommonResponse createOrder(long userId, OrderRequest orderRequest) {
        User userEntity = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        Address address = addressRepository.findById(orderRequest.getAddressId()).orElseThrow(
                () -> new IllegalArgumentException("Address not found")
        );

        Order order = orderConverter.convertOrderRequestToEntity(orderRequest);
        order.setAddress(address);
        order.setUser(userEntity);
        order.setOrderLineList(Collections.singletonList(new OrderLine()));

        Order orderEntity = orderRepository.save(order);

        List<CartItem> cartItemList = userEntity.getCart().getCartItemList();
        List<OrderLine> orderLineList = new ArrayList<>();
        for (CartItem cartItem : cartItemList){

            int quantity = cartItem.getQuantity();
            double price = cartItem.getPrice();

            OrderLine orderLine = OrderLine.builder()
                    .quantity(quantity)
                    .price(price)
                    .subtotal(quantity * price)
                    .product(cartItem.getProduct())
                    .variant(cartItem.getVariant())
                    .order(orderEntity)
                    .build();

            orderLineList.add(orderLine);
        }
        orderEntity.setOrderLineList(orderLineList);
        Order orderAfterSave = orderRepository.save(orderEntity);

//        OrderResponse orderResponse = orderConverter.convertOrderEntityToResponse(orderAfterSave);

        return createCommonResponse(false,"Create Order Successfully",HttpStatus.OK);

    }

    @Override
    public CommonResponse getAllCartItemToCreateOrder(long id) {
        User userEntity = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        List<CartItem> cartItemList = userEntity.getCart().getCartItemList();
        List<OrderLineResponse> orderLineResponses = orderConverter.convertListCartItemEntityToListOrderLineResponse(cartItemList);
        return createCommonResponse(orderLineResponses,"Get all Product in Order successfully", HttpStatus.OK);
    }


    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }
}
