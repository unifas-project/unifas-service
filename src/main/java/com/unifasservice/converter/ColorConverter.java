package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ColorResponse;
import com.unifasservice.entity.Color;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ColorConverter {

    public ColorResponse convertToColorResponseDto(Color color) {
        ColorResponse colorResponse = new ColorResponse();

        colorResponse.setId(color.getId());
        colorResponse.setName(color.getName());
        return colorResponse;
    }

    public List<ColorResponse> convertToColorResponsesDto (List<Color> colors) {

        List<ColorResponse> colorResponseList = new ArrayList<>();

        for (Color color : colors) {
            colorResponseList.add(convertToColorResponseDto(color));
        }
        return colorResponseList;
    }


    public ColorResponse colorToColorResponse(Color color) {
        return ColorResponse.builder()
                .id(color.getId())
                .name(color.getName())
                .code(color.getCode())
                .acronym(color.getAcronym())
                .build();
    }
}
