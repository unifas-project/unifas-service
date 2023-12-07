package com.unifasservice.service;

import com.unifasservice.dto.payload.request.CreateProductRequest;
import com.unifasservice.dto.payload.response.CreateProductResponse;
import com.unifasservice.dto.payload.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();

//    CreateProductResponse createProduct(CreateProductRequest productRequest);
}
