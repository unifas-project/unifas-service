package com.unifasservice.service.impl;

import com.unifasservice.converter.SubCategoryConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.SubCategoryResponse;
import com.unifasservice.entity.SubCategory;
import com.unifasservice.repository.SubCategoryRepository;
import com.unifasservice.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    private final SubCategoryConverter subCategoryConverter;

    @Override
    public CommonResponse findAll() {
        CommonResponse commonResponse = new CommonResponse();
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        if(subCategoryList.isEmpty()) {
            commonResponse.setData(null);
            commonResponse.setMessage("SubCategories not found");
            commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
        }
        else {
            List<SubCategoryResponse> subCategoryResponseList = subCategoryConverter.listSubCategoryToListSubCategoryResponse(subCategoryList);
            commonResponse.setData(subCategoryResponseList);
            commonResponse.setMessage("Accessed the subCategories successfully");
            commonResponse.setStatusCode(HttpStatus.OK);
        }
        return commonResponse;
    }
}
