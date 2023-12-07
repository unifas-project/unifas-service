package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.SizeResponse;
import com.unifasservice.entity.Size;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SizeConverter {

    public SizeResponse convertToSizeResponseDto(Size size) {
        SizeResponse sizeResponse = new SizeResponse();

        sizeResponse.setId(size.getId());
        sizeResponse.setName(size.getName());
        return sizeResponse;
    }

    public List<SizeResponse> convertToSizeResponsesDto (List<Size> sizes) {

        List<SizeResponse> sizeResponseList = new ArrayList<>();

        for (Size size : sizes) {
            sizeResponseList.add(convertToSizeResponseDto(size));
        }
        return sizeResponseList;
    }

    public SizeResponse sizeToSizeResponse(Size size) {
        return SizeResponse.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }
}
