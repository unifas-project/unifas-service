package com.unifasservice.service;

import com.unifasservice.dto.response.SubCategoryResponseDto;

import java.util.List;
public interface SubCategoryService {
    List<SubCategoryResponseDto> findAll();
}
