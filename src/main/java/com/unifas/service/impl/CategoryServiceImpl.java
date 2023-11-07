package com.unifas.service.impl;

import com.unifas.converter.CategoryConverter;
import com.unifas.dto.response.CategoryResponseDTO;
import com.unifas.entity.Category;
import com.unifas.repository.CategoryRepository;
import com.unifas.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryResponseDTO> findAll() {
        List<CategoryResponseDTO> categoriesResponseDTO = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for(Category category : categories) {
                CategoryResponseDTO categoryResponseDTO =
                        categoryConverter.categoryToCategoryResponseDTO(category);
                categoriesResponseDTO.add(categoryResponseDTO);
        }
        return categoriesResponseDTO;
    }
}
