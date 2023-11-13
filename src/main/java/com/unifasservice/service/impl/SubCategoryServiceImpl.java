package com.unifasservice.service.impl;

import com.unifasservice.converter.SubCategoryConverter;
import com.unifasservice.dto.response.SubCategoryResponseDto;
import com.unifasservice.entity.SubCategory;
import com.unifasservice.repository.SubCategoryRepository;
import com.unifasservice.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SubCategoryConverter subCategoryConverter;

    @Override
    public List<SubCategoryResponseDto> findAll() {
        List<SubCategoryResponseDto> subCategoriesResponseDTO = new ArrayList<>();
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        for ( SubCategory subCategory : subCategories ) {
                SubCategoryResponseDto subCategoryResponseDTO =
                        subCategoryConverter.subCategoryToSubCategoryResponseDTO(subCategory);
                subCategoriesResponseDTO.add(subCategoryResponseDTO);
        }
        return subCategoriesResponseDTO;
    }
}
