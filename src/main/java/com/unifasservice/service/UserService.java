package com.unifasservice.service;


import com.unifasservice.dto.payload.request.UserLoginRequest;
import com.unifasservice.dto.payload.request.UserRegisterRequest;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.response.UserLoginResponse;
import com.unifasservice.dto.payload.response.UserRegisterResponse;


public interface UserService {
    CommonResponse login(UserLoginRequest login);
    CommonResponse register(UserRegisterRequest userResgisterRequestDTO);
}
