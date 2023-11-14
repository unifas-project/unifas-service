package com.unifasservice.service;


import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.ResponseDto;
import com.unifasservice.dto.response.UserLoginResponseDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;


public interface UserService {
    UserLoginResponseDTO login(UserLoginRequestDTO login);
    ResponseDto register(UserRegisterRequestDTO userResgisterRequestDTO);
}
