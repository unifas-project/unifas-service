package com.unifas.service.impl;

import com.unifas.converter.SubCategoryConverter;
import com.unifas.dto.response.SubCategoryResponseDTO;
import com.unifas.entity.SubCategory;
import com.unifas.repository.SubCategoryRepository;
import com.unifas.service.ISubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryServiceImpl implements ISubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SubCategoryConverter subCategoryConverter;

    @Override
    public List<SubCategoryResponseDTO> findAll() {
        List<SubCategoryResponseDTO> subCategoriesResponseDTO = new ArrayList<>();
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        for ( SubCategory subCategory : subCategories ) {
                SubCategoryResponseDTO subCategoryResponseDTO =
                        subCategoryConverter.subCategoryToSubCategoryResponseDTO(subCategory);
                subCategoriesResponseDTO.add(subCategoryResponseDTO);
        }
        return subCategoriesResponseDTO;
    }
}
