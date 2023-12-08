package com.unifasservice.service;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.*;
import com.unifasservice.dto.payload.response.CodePassResponse;
import com.unifasservice.dto.payload.response.UserDetailResponse;


public interface UserService {
    CommonResponse login(UserLoginRequest login);
    CommonResponse register(UserRegisterRequest userResgisterRequest);
    CodePassResponse createCodePass(ForgetPassRequest forgetPassRequest);
    boolean changePass(ChangePassRequest changePassRequest);

    UserDetailResponse getUserByUsername(String username) throws Exception;
    UserDetailResponse updateUserByUsername(String username, UserDetailRequest userDetailRequest) throws Exception;
    UserDetailResponse changePasswordByUsername(String username, UserPasswordRequest userPasswordRequest) throws Exception;

}
