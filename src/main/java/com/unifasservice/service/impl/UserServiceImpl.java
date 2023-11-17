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
        try {
            String email = login.getEmail();
            String password = login.getPassword();

            if (email == null || password == null) {
                throw new IllegalArgumentException("Username and password are required.");
            }

            User user = userRepository.findByEmail(email);

            if (user != null) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    UserLoginResponse loginResponse = userLoginConverter.userToUserLoginDTO(user);
                    String token = jwtTokenUtil.generateToken(user);
                    loginResponse.setToken(token);
                    loginResponse.setRole(user.getRole());

                    return CommonResponse.builder()
                            .data(loginResponse)
                            .message("Logged in Successfully !")
                            .statusCode(HttpStatus.OK)
                            .build();
                } else {
                    throw new RuntimeException("Wrong password");
                }
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (IllegalArgumentException e) {
            return CommonResponse.builder()
                    .message(e.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (RuntimeException e) {
            return CommonResponse.builder()
                    .message(e.getMessage())
                    .statusCode(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }


    @Override
    public CommonResponse register(UserRegisterRequest userRegisterRequest) {


        String password = userRegisterRequest.getPassword();

        String hashCode = passwordEncoder.encode(password);

        UserRegisterResponse responseDTO;

        try {
            User userNameCheck = userRepository.findByUsername(userRegisterRequest.getUsername());
            if (userNameCheck != null) {

                return CommonResponse.builder()
                        .message("This account already exists")
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .data(null)
                        .build();

            }
            User emailCheck = userRepository.findByEmail(userRegisterRequest.getEmail());
            if (emailCheck != null) {

                return CommonResponse.builder()
                        .message("Email exists, please enter another email!")
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .data(null)
                        .build();

            }

            User user = userRegisterConverter.convertDTORequestToEntity(userRegisterRequest);
            user.setPassword(hashCode);
            user.setDeleted(false);
            user.setRole("ROLE_USER");

            Cart cartUser = new Cart();
            cartUser.setUser(user);
            user.setCart(cartUser);
            userRepository.save(user);
            responseDTO = userRegisterConverter.convertEntityResponseToDTO(user);

            return CommonResponse.builder()
                    .message("Register Success!")
                    .statusCode(HttpStatus.CREATED)
                    .data(responseDTO)
                    .build();



        } catch (Exception e) {

            return CommonResponse.builder()
                    .message("Register Failure!")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();

        }

    }

}
