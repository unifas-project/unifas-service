package com.unifasservice.service;


import com.unifasservice.dto.payload.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface SizeService {
    ResponseEntity<CommonResponse> getAllSize();
}
