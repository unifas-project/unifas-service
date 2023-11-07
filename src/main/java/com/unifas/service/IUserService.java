package com.unifas.service;


import com.unifas.dto.request.UserLoginRequestDTO;
import com.unifas.dto.response.UserLoginResponseDTO;


public interface IUserService {
    UserLoginResponseDTO login(UserLoginRequestDTO login);
}
