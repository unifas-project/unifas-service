package com.unifasservice.service.impl;

import com.unifasservice.converter.SizeConverter;
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
    private final SizeConverter sizeConverter;

    @Override
    public CommonResponse findAllSizes() {
        try {
            List<Size> sizes = sizeRepository.findAll();
            List<SizeResponse> sizeResponses = sizeConverter.convertToSizeResponsesDto(sizes);
            return createCommonResponse(sizeResponses, "Get all sizes successfully", HttpStatus.OK);
        } catch (Exception e) {
            return createCommonResponse(null, "Error fetching sizes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }





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
