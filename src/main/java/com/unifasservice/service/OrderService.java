package com.unifasservice.service;


import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.OrderRequest;
import org.springframework.security.core.Authentication;

public interface OrderService {

    CommonResponse createOrder(long userId, OrderRequest orderRequest);

    CommonResponse getAllCartItemToCreateOrder(long id);
}
