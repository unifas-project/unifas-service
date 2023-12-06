package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.ColorResponse;
import com.unifasservice.dto.payload.response.SizeResponse;
import com.unifasservice.dto.payload.response.VariantResponse;
import com.unifasservice.entity.Color;
import com.unifasservice.entity.Size;
import com.unifasservice.entity.Variant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class VariantResponseConverter implements Function<List<Variant>,List<VariantResponse>> {

    private final Function<Color, ColorResponse> colorResponseFunction;
    private final Function<Size, SizeResponse> sizeResponseFunction;

    @Override
    public List<VariantResponse> apply(List<Variant> variants) {
        List<VariantResponse> variantResponseList = new ArrayList<>();
        for (Variant element : variants){
            VariantResponse variantResponse = VariantResponse.builder()
                    .colorResponse(colorResponseFunction.apply(element.getColor())).sizeResponse(sizeResponseFunction.apply(element.getSize())).build();
            variantResponseList.add(variantResponse);
        }
        return variantResponseList;
    }
}
