package com.unifas.service;

import com.unifas.dto.response.SubCategoryResponseDTO;

import java.util.List;
public interface ISubCategoryService {
    List<SubCategoryResponseDTO> findAll();
}
