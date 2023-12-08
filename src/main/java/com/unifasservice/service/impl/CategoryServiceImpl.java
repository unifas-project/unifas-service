package com.unifasservice.service.impl;

import com.unifasservice.converter.CategoryConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.CategoryResponse;
import com.unifasservice.entity.Category;
import com.unifasservice.repository.CategoryRepository;
import com.unifasservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryConverter categoryConverter;
    private final CategoryRepository categoryRepository;
    @Override
    public CommonResponse findAll() {
        CommonResponse commonResponse = new CommonResponse();
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty()) {
            commonResponse.setData(null);
            commonResponse.setMessage("Categories not found");
            commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
        }
        else {
            List<CategoryResponse> categoryResponseList = categoryConverter.listCategoryToListCategoryResponse(categoryList);
            commonResponse.setData(categoryResponseList);
            commonResponse.setMessage("Accessed the categories successfully");
            commonResponse.setStatusCode(HttpStatus.OK);
        }
        return commonResponse;
    }
}
