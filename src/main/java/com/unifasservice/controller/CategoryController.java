package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<CommonResponse> getAllCategory() {
        CommonResponse commonResponse = categoryService.findAll();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
