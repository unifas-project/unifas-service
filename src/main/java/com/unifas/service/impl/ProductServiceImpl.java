package com.unifas.service.impl;

import com.unifas.converter.ProductConverter;
import com.unifas.dto.response.ProductResponseDTO;
import com.unifas.entity.Product;
import com.unifas.repository.ProductRepository;
import com.unifas.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public List<ProductResponseDTO> findAll() {
        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for ( Product product : products) {
                ProductResponseDTO productResponseDTO = productConverter.productToProductResponseDTO(product);
                productResponseDTOs.add(productResponseDTO);
        }
        return productResponseDTOs;
    }
}
