package com.unifasservice.service;
import com.unifasservice.dto.payload.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface SizeService {
    CommonResponse findAllSizes();
    CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode);

    ResponseEntity<CommonResponse> getAllSize();
}
