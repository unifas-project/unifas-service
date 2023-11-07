package com.unifas.converter;

import com.unifas.dto.response.ProductResponseDTO;
import com.unifas.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductResponseDTO productToProductResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setStar(product.getStar());
        return productResponseDTO;
    }
}
