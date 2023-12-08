package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/sizes")
@CrossOrigin("*")
public class SizeController {

    private final SizeService sizeService;


    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllSizes() {
        CommonResponse sizes = sizeService.findAllSizes();
        return new ResponseEntity<>(sizes, HttpStatus.OK);
    }
    @GetMapping("/sizes")
    public ResponseEntity<CommonResponse> getAllSize(){
        return sizeService.getAllSize();
    }
}
