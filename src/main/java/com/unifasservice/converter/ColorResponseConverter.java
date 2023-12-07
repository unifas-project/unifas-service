package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ColorResponse;
import com.unifasservice.entity.Color;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ColorResponseConverter implements Function<Color, ColorResponse> {
    @Override
    public ColorResponse apply(Color color) {
        ColorResponse colorResponse = new ColorResponse();
        BeanUtils.copyProperties(color,colorResponse);
        return colorResponse;
    }
}