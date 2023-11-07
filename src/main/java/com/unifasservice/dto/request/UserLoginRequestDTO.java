package com.unifasservice.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginRequestDTO {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
}
