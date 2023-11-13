package com.unifasservice.converter;

import com.unifasservice.dto.response.SubCategoryResponseDto;
import com.unifasservice.entity.SubCategory;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryConverter {
    public SubCategoryResponseDto subCategoryToSubCategoryResponseDTO(SubCategory subCategory) {
        SubCategoryResponseDto subCategoryResponseDTO = new SubCategoryResponseDto();
        subCategoryResponseDTO.setId(subCategory.getId());
        subCategoryResponseDTO.setName(subCategory.getName());
        subCategoryResponseDTO.setCategoryId(subCategory.getCategory().getId());
        return subCategoryResponseDTO;
    }
}
