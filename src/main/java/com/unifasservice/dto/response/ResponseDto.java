package com.unifasservice.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@Builder
public class ResponseDto {
    private String message;
    private HttpStatus status;
    private Object data;
}
