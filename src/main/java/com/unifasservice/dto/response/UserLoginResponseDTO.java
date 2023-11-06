package com.unifasservice.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class UserLoginResponseDTO {
    private int id;
    private String username;
    private String message;

}
