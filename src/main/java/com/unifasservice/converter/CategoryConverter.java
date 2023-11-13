package com.unifasservice.converter;

import com.unifasservice.dto.response.CategoryResponseDto;
import com.unifasservice.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryResponseDto categoryToCategoryResponseDTO(Category category) {
        CategoryResponseDto categoryResponseDTO = new CategoryResponseDto();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setGender(category.getGender());
        return categoryResponseDTO;
    }
}
