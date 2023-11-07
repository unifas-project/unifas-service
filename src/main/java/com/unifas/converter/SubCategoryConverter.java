package com.unifas.converter;

import com.unifas.dto.response.SubCategoryResponseDTO;
import com.unifas.entity.SubCategory;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryConverter {
    public SubCategoryResponseDTO subCategoryToSubCategoryResponseDTO(SubCategory subCategory) {
        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
        subCategoryResponseDTO.setId(subCategory.getId());
        subCategoryResponseDTO.setName(subCategory.getName());
        subCategoryResponseDTO.setCategoryId(subCategory.getCategory().getId());
        return subCategoryResponseDTO;
    }
}
