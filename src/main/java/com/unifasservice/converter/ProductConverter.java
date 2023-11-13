package com.unifasservice.converter;

import com.unifasservice.dto.response.ProductResponseDto;
import com.unifasservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductResponseDto productToProductResponseDTO(Product product) {
        ProductResponseDto productResponseDTO = new ProductResponseDto();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setStar(product.getStar());
        return productResponseDTO;
    }
}
