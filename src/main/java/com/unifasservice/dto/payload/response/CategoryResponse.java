package com.unifasservice.dto.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private long id;
    private String name;
    private String gender;
}
