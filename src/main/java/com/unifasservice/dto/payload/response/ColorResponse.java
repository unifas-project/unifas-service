package com.unifasservice.dto.payload.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorResponse {

    private long id;

    private String name;

    private String code;

    private String acronym;
}