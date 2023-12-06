package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.SizeResponse;
import com.unifasservice.entity.Size;
import org.springframework.stereotype.Component;

@Component
public class SizeConverter {
    public SizeResponse sizeToSizeResponse(Size size) {
        return SizeResponse.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }
}
