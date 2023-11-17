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

                    return getCommonResponse("Logged in Successfully !", HttpStatus.OK, loginResponse);
                } else {
                    throw new RuntimeException("Wrong password");
                }
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (IllegalArgumentException e) {
            return getCommonResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        } catch (RuntimeException e) {
            return getCommonResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, null);
        }
    }



    @Override
    public CommonResponse register(UserRegisterRequest userRegisterRequest) {
        try {
            String password = userRegisterRequest.getPassword();
            String hashedPassword = passwordEncoder.encode(password);

            User userNameCheck = userRepository.findByUsername(userRegisterRequest.getUsername());
            if (userNameCheck != null) {
                return getCommonResponse("This account already exists", HttpStatus.BAD_REQUEST, null);
            }

            User emailCheck = userRepository.findByEmail(userRegisterRequest.getEmail());
            if (emailCheck != null) {
                return getCommonResponse("Email exists, please enter another email!", HttpStatus.BAD_REQUEST, null);
            }


            User user = userRegisterConverter.convertDTORequestToEntity(userRegisterRequest);
            user.setPassword(hashedPassword);
            user.setDeleted(false);

            Cart cartUser = new Cart();
            cartUser.setUser(user);
            user.setCart(cartUser);
            userRepository.save(user);

            UserRegisterResponse responseDTO = userRegisterConverter.convertEntityResponseToDTO(user);

            return getCommonResponse("Register Success!", HttpStatus.CREATED, responseDTO);

        } catch (Exception e) {
            e.printStackTrace();

            return getCommonResponse("Register Failure!", HttpStatus.BAD_REQUEST, null);
        }
    }


    private CommonResponse getCommonResponse(String message, HttpStatus status, Object data) {
        CommonResponse commonResponse = CommonResponse.builder()
                .message(message)
                .statusCode(status)
                .data(data)
                .build();
        return commonResponse;

    }

}
