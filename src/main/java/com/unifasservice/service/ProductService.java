package com.unifasservice.service;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.ProductResponse;

import java.util.List;

public interface ProductService {
    CommonResponse findAll();

    CommonResponse getProductById(long id);
}
