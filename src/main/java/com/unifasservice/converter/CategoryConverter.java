package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.CategoryResponse;
import com.unifasservice.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {
    public CategoryResponse categoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .gender(category.getGender())
                .build();
    }
    public List<CategoryResponse> listCategoryToListCategoryResponse(List<Category> categoryList) {
        List<CategoryResponse> list = new ArrayList<>();
        for (Category category : categoryList) {
            list.add(categoryToCategoryResponse(category));
        }
        return list;
    }
}
