package com.unifasservice.service;


import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.response.UserLoginResponseDTO;
import org.springframework.stereotype.Service;


public interface IUserService {
    UserLoginResponseDTO login(UserLoginRequestDTO login);
}
