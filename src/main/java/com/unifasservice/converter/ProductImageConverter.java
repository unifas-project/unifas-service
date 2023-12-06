package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ProductImageResponse;
import com.unifasservice.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductImageConverter {
    public ProductImageResponse productImageToProductImageResponse(ProductImage productImage) {
        return ProductImageResponse.builder()
                .id(productImage.getId())
                .url(productImage.getUrl())
                .build();
    }
    public List<ProductImageResponse> listProductImageToListProductImageResponse(List<ProductImage> productImageList) {
        List<ProductImageResponse> list = new ArrayList<>();
        for (ProductImage productImage : productImageList) {
            list.add(productImageToProductImageResponse(productImage));
        }
        return list;
    }

}
