package com.unifasservice.service;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.UserLoginRequest;
import com.unifasservice.dto.payload.request.UserRegisterRequest;


public interface UserService {
    CommonResponse login(UserLoginRequest login);
    CommonResponse register(UserRegisterRequest userResgisterRequestDTO);
}
