package com.unifasservice.controller;

import com.unifasservice.dto.payload.request.CreateProductRequest;
import com.unifasservice.dto.payload.response.CreateProductResponse;
import com.unifasservice.dto.payload.response.ProductResponse;
import com.unifasservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        List<ProductResponse> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
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
