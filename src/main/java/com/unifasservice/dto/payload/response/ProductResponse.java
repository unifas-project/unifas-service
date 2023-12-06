package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {

    private long id;

    private String name;

    private double price;

    private int stock;

    private String description;

    private int star;

    private CategoryResponse category;

    private SubCategoryResponse subCategory;

    private List<ProductImageResponse> imageProductList ;

    private List<ReviewResponse> reviews ;

    private List<VariantResponse> variantList;
}
