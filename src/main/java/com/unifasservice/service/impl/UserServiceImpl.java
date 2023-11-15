package com.unifasservice.service.impl;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.security.JwtTokenUtil;
import com.unifasservice.converter.UserLoginConverter;
import com.unifasservice.dto.payload.request.UserLoginRequest;
import com.unifasservice.dto.payload.response.UserLoginResponse;
import com.unifasservice.entity.Cart;
import com.unifasservice.entity.User;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.converter.UserRegisterConverter;
import com.unifasservice.dto.payload.request.UserRegisterRequest;
import com.unifasservice.dto.payload.response.UserRegisterResponse;
import com.unifasservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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


    public CommonResponse login(UserLoginRequest login) {

        CommonResponse commonResponse = new CommonResponse();

        String email = login.getEmail();
        String password = login.getPassword();

        if (email == null || password == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {

                UserLoginResponse loginResponseDTO = userLoginConverter.userToUserLoginDTO(user);


                String token = jwtTokenUtil.generateToken(user);
                loginResponseDTO.setToken(token);
                loginResponseDTO.setRole(user.getRole());


                commonResponse.builder()
                        .message("Logged in Successfully !")
                        .statusCode(HttpStatus.OK)
                        .data(loginResponseDTO)
                        .build();
                return commonResponse;


            } else {
                throw new RuntimeException("Wrong password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
    @Override
    public CommonResponse register(UserRegisterRequest userRegisterRequestDTO) {

        CommonResponse commonResponse =  new CommonResponse();

        String password = userRegisterRequestDTO.getPassword();
        String hashCode = passwordEncoder.encode(password);

        UserRegisterResponse responseDTO = new UserRegisterResponse();

        try {
            User userNameCheck = userRepository.findByUsername(userRegisterRequestDTO.getUsername());
            if (userNameCheck != null) {
                commonResponse.builder()
                        .message("This account already exists")
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .data(null)
                        .build();
                return commonResponse;
            }
            User emailCheck = userRepository.findByEmail(userRegisterRequestDTO.getEmail());
            if (emailCheck != null) {
                commonResponse.builder()
                        .message("Email exists, please enter another email!")
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .data(null)
                        .build();
                return commonResponse;


            }

            User user = userRegisterConverter.convertDTORequestToEntity(userRegisterRequestDTO);
            user.setPassword(hashCode);
            user.setDeleted(false);

            Cart cartUser = new Cart();
            cartUser.setUser(user);
            user.setCart(cartUser);
            userRepository.save(user);
            responseDTO = userRegisterConverter.convertEntityResponseToDTO(user);



            commonResponse.builder()
                    .message("Register Success!")
                    .statusCode(HttpStatus.CREATED)
                    .data(responseDTO)
                    .build();
            return commonResponse;


        } catch (Exception e) {

            commonResponse.builder()
                    .message("Register Failure!")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();

            return commonResponse;
        }

    }
}
