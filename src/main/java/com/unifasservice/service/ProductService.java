package com.unifasservice.service;

import com.unifasservice.dto.payload.CommonResponse;

public interface ProductService {

    CommonResponse searchProductByName(String name);

    CommonResponse findAll();

    CommonResponse getProductById(long id);

    CommonResponse getProductByCategoryId(long categoryId);

    CommonResponse getProductBySubCategoryId(long subCategoryId);
}
