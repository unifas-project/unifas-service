package com.unifasservice.controller;

import com.unifasservice.dto.response.CategoryResponseDTO;
import com.unifasservice.service.ICategoryService;
import com.unifasservice.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategory() {
        List<CategoryResponseDTO> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
