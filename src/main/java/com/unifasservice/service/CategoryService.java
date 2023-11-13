package com.unifasservice.service;


import com.unifasservice.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> findAll();
}
