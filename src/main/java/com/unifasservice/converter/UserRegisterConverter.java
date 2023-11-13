package com.unifasservice.converter;


import com.unifasservice.dto.request.UserRegisterRequestDto;
import com.unifasservice.dto.response.UserRegisterResponseDto;
import com.unifasservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterConverter {
    public UserRegisterResponseDto convertEntityResponseToDTO(User user){
        UserRegisterResponseDto userRegisterResponseDTO = new UserRegisterResponseDto();
        userRegisterResponseDTO.setId(user.getId());
        userRegisterResponseDTO.setUsername(user.getUsername());
        return userRegisterResponseDTO;
    }
    public User convertDTORequestToEntity(UserRegisterRequestDto userRegisterRequestDTO){
        User user = new User();
        user.setId(userRegisterRequestDTO.getId());
        user.setUsername(userRegisterRequestDTO.getUsername());
        user.setPassword(userRegisterRequestDTO.getPassword());
        user.setEmail(userRegisterRequestDTO.getEmail());
        user.setPhoneNumber(userRegisterRequestDTO.getPhoneNumber());
        user.setRole(userRegisterRequestDTO.getRole());
        return user;
    }
}
