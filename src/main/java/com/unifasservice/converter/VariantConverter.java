package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.VariantResponse;
import com.unifasservice.entity.Variant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VariantConverter {
    private final ColorConverter colorConverter;
    private final SizeConverter sizeConverter;

    public VariantConverter(ColorConverter colorConverter, SizeConverter sizeConverter) {
        this.colorConverter = colorConverter;
        this.sizeConverter = sizeConverter;
    }
    public VariantResponse variantToVariantResponse(Variant variant) {
        return VariantResponse.builder()
                .id(variant.getId())
                .colorResponse(colorConverter.colorToColorResponse(variant.getColor()))
                .sizeResponse(sizeConverter.sizeToSizeResponse(variant.getSize()))
                .build();
    }
    public List<VariantResponse> listVariantToListVariantResponse(List<Variant> variantList) {
        List<VariantResponse> list = new ArrayList<>();
        for (Variant variant : variantList) {
            list.add(variantToVariantResponse(variant));
        }
        return list;
    }
}
