package com.unifas.dto.response;

import lombok.Data;

@Data
public class SubCategoryResponseDTO {
    private long id;
    private String name;
    private long categoryId;
}