package com.unifasservice.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequestDTO {
    private Long id;
    private String username;

    private String password;

    private String email;
    private String phoneNumber;

    private String address;
}
