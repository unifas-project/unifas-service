package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.SubCategoryResponse;
import com.unifasservice.entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubCategoryConverter {

    private final CategoryConverter categoryConverter;

    public SubCategoryConverter(CategoryConverter categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    public SubCategoryResponse subCategoryToSubCategoryResponse(SubCategory subCategory) {
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .category(categoryConverter.categoryToCategoryResponse(subCategory.getCategory()))
                .build();
    }
    public List<SubCategoryResponse> listSubCategoryToListSubCategoryResponse(List<SubCategory> subCategoryList) {
        List<SubCategoryResponse> list = new ArrayList<>();
        for (SubCategory subCategory : subCategoryList) {
            list.add(subCategoryToSubCategoryResponse(subCategory));
        }
        return list;
    }
}
