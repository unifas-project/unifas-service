package com.unifas.service;


import com.unifas.dto.response.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDTO> findAll();
}
