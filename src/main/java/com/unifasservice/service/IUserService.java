package com.unifasservice.service;


import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.UserLoginResponseDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;


public interface IUserService {
    UserLoginResponseDTO login(UserLoginRequestDTO login);
    UserRegisterResponseDTO register(UserRegisterRequestDTO userResgisterRequestDTO);
}
