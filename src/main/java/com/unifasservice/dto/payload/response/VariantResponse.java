package com.unifasservice.dto.payload.response;

import com.unifasservice.entity.Color;
import com.unifasservice.entity.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariantResponse {

    private long id;

    private ColorResponse color;

    private SizeResponse size;
}
