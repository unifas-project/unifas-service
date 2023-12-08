package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("")
    public ResponseEntity<CommonResponse> getAll() {
        CommonResponse commonResponse = productService.findAll();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<CommonResponse> getProductById(@PathVariable("productId") long id) {
        CommonResponse commonResponse = productService.getProductById(id);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CommonResponse> getProductByCategoryId(@PathVariable("categoryId") long categoryId) {
        CommonResponse commonResponse = productService.getProductByCategoryId(categoryId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
    @GetMapping("/subCategory/{subCategoryId}")
    public ResponseEntity<CommonResponse> getProductBySubCategoryId(@PathVariable("subCategoryId") long subCategoryId) {
        CommonResponse commonResponse = productService.getProductBySubCategoryId(subCategoryId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    //    @PostMapping("")
//    public ResponseEntity<?> save(
//            @Valid @RequestBody CreateProductRequest productRequest, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
//        }
//        CreateProductResponse productResponseDTO = productService.createProduct(productRequest);
//        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
//    }
}
