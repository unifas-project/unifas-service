package com.unifas.converter;


import com.unifas.dto.request.UserRegisterRequestDTO;
import com.unifas.dto.response.UserRegisterResponseDTO;
import com.unifas.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterConverter {
    public UserRegisterResponseDTO convertEntityResponseToDTO(User user){
        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO.setId(user.getId());
        userRegisterResponseDTO.setUsername(user.getUsername());
        return userRegisterResponseDTO;
    }
    public User convertDTORequestToEntity(UserRegisterRequestDTO userRegisterRequestDTO){
        User user = new User();
        user.setId(userRegisterRequestDTO.getId());
        user.setUsername(userRegisterRequestDTO.getUsername());
        user.setPassword(userRegisterRequestDTO.getPassword());
        user.setEmail(user.getEmail());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setRole(user.getRole());
        return user;
    }
}
