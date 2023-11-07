package com.unifas.converter;

import com.unifas.dto.response.CategoryResponseDTO;
import com.unifas.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryResponseDTO categoryToCategoryResponseDTO(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setGender(category.getGender());
        return categoryResponseDTO;
    }
}
