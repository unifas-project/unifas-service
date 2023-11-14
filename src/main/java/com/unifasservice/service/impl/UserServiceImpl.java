package com.unifasservice.service.impl;

import com.unifasservice.converter.UserLoginConverter;
import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.response.ResponseDto;
import com.unifasservice.dto.response.UserLoginResponseDTO;
import com.unifasservice.entity.User;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.converter.UserRegisterConverter;
import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;
import com.unifasservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final UserLoginConverter userLoginConverter;

    private final UserRegisterConverter userRegisterConverter;

    public UserLoginResponseDTO login(UserLoginRequestDTO login) {
        String email = login.getEmail();
        String password = login.getPassword();

        if (email == null || password == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (password.equals(user.getPassword())) {
                UserLoginResponseDTO loginResponseDTO = userLoginConverter.userToUserLoginDTO(user);
                loginResponseDTO.setMessage("Logged in successfully !");
                return loginResponseDTO;
            } else {
                throw new RuntimeException("Wrong password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public ResponseDto register(UserRegisterRequestDTO userResgisterRequestDTO) {
        ResponseDto responseDto;
        User userNameCheck = userRepository.findByUsername(userResgisterRequestDTO.getUsername());
        if (userNameCheck != null) {
            responseDto = getResponseDto("Register False!", HttpStatus.BAD_REQUEST, false);
            return responseDto;
        }
        User emailCheck = userRepository.findByEmail(userResgisterRequestDTO.getEmail());
        if (emailCheck != null) {
            responseDto = getResponseDto("Register False!", HttpStatus.BAD_REQUEST, false);
            return responseDto;
        }
        try {
            User user = userRegisterConverter.convertDTORequestToEntity(userResgisterRequestDTO);
            userRepository.save(user);
        } catch (Exception e) {
            responseDto = getResponseDto("Register False!", HttpStatus.BAD_REQUEST, false);
            return responseDto;
        }
        responseDto = getResponseDto("Register Success!", HttpStatus.OK, true);
        return responseDto;
    }

    private ResponseDto getResponseDto(String message, HttpStatus status, Object data) {
        ResponseDto responseDto = ResponseDto.builder()
                .message(message)
                .status(status)
                .data(data)
                .build();
        return responseDto;
    }
}
