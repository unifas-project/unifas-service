package com.unifasservice.service;


import com.unifas.dto.request.UserLoginRequestDTO;
import com.unifas.dto.request.UserRegisterRequestDTO;
import com.unifas.dto.response.UserLoginResponseDTO;
import com.unifas.dto.response.UserRegisterResponseDTO;


public interface IUserService {
    UserLoginResponseDTO login(UserLoginRequestDTO login);

    UserRegisterResponseDTO register(UserRegisterRequestDTO userResgisterRequestDTO);
}
