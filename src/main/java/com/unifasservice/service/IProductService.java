package com.unifasservice.service;

import com.unifasservice.dto.response.ProductResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> findAll();
}
