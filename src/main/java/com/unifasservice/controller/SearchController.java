package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SearchController {
    private final ProductService productService;
    @GetMapping("/search")
    public ResponseEntity<CommonResponse> searchProductByName(@RequestParam String name){
        CommonResponse commonResponse = productService.searchProductByName(name);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
