package com.unifas.dto.response;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private long id;
    private String username;
    private String message;

}