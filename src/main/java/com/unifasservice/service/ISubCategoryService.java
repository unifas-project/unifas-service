package com.unifasservice.service;

import com.unifasservice.dto.response.SubCategoryResponseDTO;

import java.util.List;
public interface ISubCategoryService {
    List<SubCategoryResponseDTO> findAll();
}