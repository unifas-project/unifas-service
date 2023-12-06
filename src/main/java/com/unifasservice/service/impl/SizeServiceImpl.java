package com.unifasservice.service.impl;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.SizeResponse;
import com.unifasservice.entity.Size;
import com.unifasservice.repository.SizeRepository;
import com.unifasservice.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    private final Function<Size, SizeResponse> sizeResponseFunction;
    @Override
    public ResponseEntity<CommonResponse> getAllSize() {
        try {
            List<Size> sizeList = sizeRepository.findAll();
            List<SizeResponse> sizeResponseList = new ArrayList<>();
            for (Size element : sizeList){
                sizeResponseList.add(sizeResponseFunction.apply(element))    ;
            }
            CommonResponse commonResponse = CommonResponse.builder().message("success").statusCode(HttpStatus.OK).data(sizeResponseList).build();
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }catch (Exception e){
            CommonResponse commonResponse = CommonResponse.builder().message("error").statusCode(HttpStatus.BAD_REQUEST).data(false).build();
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
    }
}
