package com.unifasservice.service;


import com.unifasservice.dto.payload.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ColorService {
    CommonResponse findAllColors();
    CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode);
    ResponseEntity<CommonResponse> getAllColor();
}
