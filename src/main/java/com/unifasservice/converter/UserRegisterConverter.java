package com.unifasservice.converter;


import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;
import com.unifasservice.entity.User;
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
        user.setUsername(userRegisterRequestDTO.getUsername());
        user.setPassword(userRegisterRequestDTO.getPassword());
        user.setEmail(userRegisterRequestDTO.getEmail());
        user.setRole("ROLE_USER");
        return user;
    }
}
