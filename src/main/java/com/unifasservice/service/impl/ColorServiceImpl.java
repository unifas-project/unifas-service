package com.unifasservice.service.impl;

import com.unifasservice.converter.ColorConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.ColorResponse;
import com.unifasservice.entity.Color;
import com.unifasservice.repository.ColorRepository;
import com.unifasservice.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

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

}

