package com.unifasservice.service;


import com.unifasservice.dto.request.UserLoginRequestDto;
import com.unifasservice.dto.request.UserRegisterRequestDto;
import com.unifasservice.dto.response.UserLoginResponseDto;
import com.unifasservice.dto.response.UserRegisterResponseDto;


public interface UserService {
    UserLoginResponseDto login(UserLoginRequestDto login);
    UserRegisterResponseDto register(UserRegisterRequestDto userResgisterRequestDTO);
}
