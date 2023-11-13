package com.unifasservice.service;

import com.unifasservice.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> findAll();
}
