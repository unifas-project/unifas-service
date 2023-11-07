package com.unifas.service;

import com.unifas.dto.response.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> findAll();
}
