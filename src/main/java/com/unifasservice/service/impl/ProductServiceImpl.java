package com.unifasservice.service.impl;

import com.unifasservice.converter.ProductConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.ProductResponse;
import com.unifasservice.dto.payload.response.SearchProduct;
import com.unifasservice.entity.Product;
import com.unifasservice.repository.ProductRepository;
import com.unifasservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductConverter productConverter;

    private final Function<List<Product>, List<SearchProduct>> searchProductFunction;

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
        }
        else {
            commonResponse.setData(productConverter.productToProductResponse(product.get()));
            commonResponse.setMessage("Accessed the product successfully");
            commonResponse.setStatusCode(HttpStatus.OK);
        }
        return commonResponse;
    }

    @Override
    public CommonResponse getProductByCategoryId(long categoryId) {
        CommonResponse commonResponse = new CommonResponse();
        List<Product> productList = productRepository.findProductByCategoryId(categoryId);
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
    public CommonResponse getProductBySubCategoryId(long subCategoryId) {
        CommonResponse commonResponse = new CommonResponse();
        List<Product> productList = productRepository.findProductBySubCategoryId(subCategoryId);
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
