package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SizeResponse {

    private long id;

    private String name;
}
