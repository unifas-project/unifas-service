package com.unifasservice.service.impl;

import com.unifasservice.converter.ProductConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.ProductResponse;
import com.unifasservice.entity.Product;
import com.unifasservice.repository.ProductRepository;
import com.unifasservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public CommonResponse findAll() {
        CommonResponse commonResponse = new CommonResponse();
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()) {
            commonResponse.setData(null);
            commonResponse.setMessage("Products not found");
            commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
        }
        else {
            List<ProductResponse> productResponseList = productConverter.listProductToListProductResponse(productList);
            commonResponse.setData(productResponseList);
            commonResponse.setMessage("Accessed the products successfully");
            commonResponse.setStatusCode(HttpStatus.OK);
        }
        return commonResponse;
    }

    @Override
    public CommonResponse getProductById(long id) {
        CommonResponse commonResponse = new CommonResponse();
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            commonResponse.setData(null);
            commonResponse.setMessage("Product not found");
            commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
            return commonResponse;
        }
        else {
            commonResponse.setData(productConverter.productToProductResponse(product.get()));
            commonResponse.setMessage("Accessed the product successfully");
            commonResponse.setStatusCode(HttpStatus.OK);
            return commonResponse;
        }
    }
}
