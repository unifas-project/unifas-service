package com.unifasservice.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProduct {
    private long id;
    private String name;
    private double price;
    private int star;
    private List<ImgResponse> imgResponseList;
    private CategoryResponse categoryResponse;
    private SubCategoryResponse subCategoryResponse;
    private List<VariantResponse> variantResponseList;
}
