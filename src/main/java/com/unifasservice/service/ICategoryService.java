package com.unifasservice.service;


import com.unifasservice.dto.response.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDTO> findAll();
}
