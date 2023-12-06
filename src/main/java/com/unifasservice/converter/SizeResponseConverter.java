package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.SizeResponse;
import com.unifasservice.entity.Size;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SizeResponseConverter implements Function<Size, SizeResponse> {
    @Override
    public SizeResponse apply(Size size) {
        SizeResponse sizeResponse = new SizeResponse();
        BeanUtils.copyProperties(size,sizeResponse);
        return sizeResponse;
    }
}
