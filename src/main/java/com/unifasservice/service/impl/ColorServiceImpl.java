package com.unifasservice.service.impl;

import com.unifasservice.converter.ColorConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.ColorResponse;
import com.unifasservice.entity.Color;
import com.unifasservice.repository.ColorRepository;
import com.unifasservice.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorConverter colorConverter;

    @Override
    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    @Override
    public CommonResponse findAllColors() {
        try {
            List<Color> colors = colorRepository.findAll();
            List<ColorResponse> colorResponses = colorConverter.convertToColorResponsesDto(colors);
            return createCommonResponse(colorResponses, "Get all colors successfully", HttpStatus.OK);
        } catch (Exception e) {
            return createCommonResponse(null, "Error fetching colors", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    private final Function<Color, ColorResponse> colorResponseFunction;

    @Override
    public ResponseEntity<CommonResponse> getAllColor() {
        try {
            List<Color> colorList = colorRepository.findAll();
            List<ColorResponse> colorResponseList = new ArrayList<>();
            for (Color element : colorList){
                colorResponseList.add(colorResponseFunction.apply(element))    ;
            }
            CommonResponse commonResponse = CommonResponse.builder().message("success").statusCode(HttpStatus.OK).data(colorResponseList).build();
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }catch (Exception e){
            CommonResponse commonResponse = CommonResponse.builder().message("error").statusCode(HttpStatus.BAD_REQUEST).data(false).build();
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
    }
}