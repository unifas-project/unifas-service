package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ColorResponse;
import com.unifasservice.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorConverter {
    public ColorResponse colorToColorResponse(Color color) {
        return ColorResponse.builder()
                .id(color.getId())
                .name(color.getName())
                .code(color.getCode())
                .acronym(color.getAcronym())
                .build();
    }
}
