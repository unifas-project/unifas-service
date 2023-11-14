package com.unifasservice.service.impl;

import com.unifasservice.configuration.JwtTokenUtil;
import com.unifasservice.converter.UserLoginConverter;
import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.response.ResponseDto;
import com.unifasservice.dto.response.UserLoginResponseDTO;
import com.unifasservice.entity.User;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.converter.UserRegisterConverter;
import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.unifasservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserLoginConverter userLoginConverter;

    private final UserRegisterConverter userRegisterConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UserLoginResponseDTO login(UserLoginRequestDTO login) {
        String email = login.getEmail();
        String password = login.getPassword();

        if (email == null || password == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                UserLoginResponseDTO loginResponseDTO = userLoginConverter.userToUserLoginDTO(user);
                loginResponseDTO.setMessage("Logged in successfully !");

                String token = jwtTokenUtil.generateToken(user);
                loginResponseDTO.setToken(token);
                loginResponseDTO.setRole(user.getRole());


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
            responseDto = getResponseDto("Username already exists!", HttpStatus.BAD_REQUEST, false);
            return responseDto;
        }
        User emailCheck = userRepository.findByEmail(userResgisterRequestDTO.getEmail());
        if (emailCheck != null) {
            responseDto = getResponseDto("Email already exists!", HttpStatus.BAD_REQUEST, false);
            return responseDto;
        }
        try {
        String password = userResgisterRequestDTO.getPassword();
        String hashCode = passwordEncoder.encode(password);
            User user = userRegisterConverter.convertDTORequestToEntity(userResgisterRequestDTO);
            user.setPassword(hashCode);
            user.setDeleted(false);
            userRepository.save(user);
        } catch (Exception e) {
            responseDto = getResponseDto("Register False!", HttpStatus.BAD_REQUEST, false);
            return responseDto;
        }
        responseDto = getResponseDto("Register Success!", HttpStatus.OK, true);
        return responseDto;
    }

    private ResponseDto getResponseDto (String message, HttpStatus status, Object data) {
        ResponseDto responseDto = ResponseDto.builder()
                .message(message)
                .status(status)
                .data(data)
                .build();
        return responseDto;

    }
}
