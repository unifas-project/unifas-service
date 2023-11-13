package com.unifasservice.service.impl;

import com.unifasservice.converter.ProductConverter;
import com.unifasservice.dto.response.ProductResponseDto;
import com.unifasservice.entity.Product;
import com.unifasservice.repository.ProductRepository;
import com.unifasservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public List<ProductResponseDto> findAll() {
        List<ProductResponseDto> productResponseDTOs = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for ( Product product : products) {
                ProductResponseDto productResponseDTO = productConverter.productToProductResponseDTO(product);
                productResponseDTOs.add(productResponseDTO);
        }
        return productResponseDTOs;
    }
}
