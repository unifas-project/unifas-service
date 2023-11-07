package com.unifas.converter;


import com.unifas.dto.response.UserLoginResponseDTO;
import com.unifas.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginConverter {
    public UserLoginResponseDTO userToUserLoginDTO (User user) {
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
        userLoginResponseDTO.setId(user.getId());
        userLoginResponseDTO.setUsername(user.getUsername());
        return userLoginResponseDTO;
    }


}
