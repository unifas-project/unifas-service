package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageResponse {

    private long id;

    private String url;
}

