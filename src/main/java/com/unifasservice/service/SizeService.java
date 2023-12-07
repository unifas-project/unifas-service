package com.unifasservice.service;


import com.unifasservice.dto.payload.CommonResponse;
import org.springframework.http.HttpStatus;

public interface SizeService {
    CommonResponse findAllSizes();
    CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode);
}
