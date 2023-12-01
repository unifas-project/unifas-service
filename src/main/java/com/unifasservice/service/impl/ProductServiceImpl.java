package com.unifasservice.service.impl;

import com.unifasservice.converter.ProductConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.ProductResponse;
import com.unifasservice.dto.payload.response.SearchProduct;
import com.unifasservice.entity.Product;
import com.unifasservice.repository.ProductRepository;
import com.unifasservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;
    private final Function<List<Product>, List<SearchProduct>> searchProductFunction;

    @Override
    public List<ProductResponse> findAll() {
        List<ProductResponse> productResponseDTOs = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for ( Product product : products) {
                ProductResponse productResponseDTO = productConverter.productToProductResponseDTO(product);
                productResponseDTOs.add(productResponseDTO);
        }
        return productResponseDTOs;
    }
    @Override
    public CommonResponse searchProductByName(String name) {
        String tempName = "%".concat(name).concat("%");
        try {
            List<Product> productList = productRepository.findAllByNameLike(tempName);
            List<SearchProduct> searchProductList = searchProductFunction.apply(productList);
            return CommonResponse.builder().message("Search success").statusCode(HttpStatus.OK).data(searchProductList).build();
        }catch (Exception e) {
            return CommonResponse.builder().message("Search error").statusCode(HttpStatus.BAD_REQUEST).data(false).build();
        }
    }
}
