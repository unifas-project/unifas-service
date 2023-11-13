package com.unifasservice.converter;


import com.unifasservice.dto.response.UserLoginResponseDto;
import com.unifasservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginConverter {
    public UserLoginResponseDto userToUserLoginDTO (User user) {
        UserLoginResponseDto userLoginResponseDTO = new UserLoginResponseDto();
        userLoginResponseDTO.setId(user.getId());
        userLoginResponseDTO.setUsername(user.getUsername());
        return userLoginResponseDTO;
    }


}
