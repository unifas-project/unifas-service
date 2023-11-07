package com.unifasservice.service.impl;

import com.unifasservice.converter.UserLoginConverter;
import com.unifasservice.converter.UserRegisterConverter;
import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.UserLoginResponseDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;
import com.unifasservice.entity.User;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoginConverter userLoginConverter;
    @Autowired
    private UserRegisterConverter userRegisterConverter;

    public UserLoginResponseDTO login(UserLoginRequestDTO login) {
        String username = login.getUsername();
        String password = login.getPassword();

        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        User user = userRepository.findByUsername(username);

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
    public UserRegisterResponseDTO register(UserRegisterRequestDTO userResgisterRequestDTO) {
        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();
        try {
            User userNameCheck = userRepository.findByUsername(userResgisterRequestDTO.getUsername());
            if (userNameCheck != null) {
                responseDTO.setMessage("This account already exists");
                return responseDTO;
            }
            User emailCheck = userRepository.findByEmail(userResgisterRequestDTO.getEmail());
            if (emailCheck != null) {
                responseDTO.setMessage("Email exists, please enter another email!");
                return responseDTO;
            }
            User user = userRegisterConverter.toEntity(userResgisterRequestDTO);
            userRepository.save(user);
            return responseDTO


//            String role = "ROLE_".concat(ERole.USER.toString());
//            String password = registerRequest.getPassword();
//            String cypherText = passwordEncoder.encode(password);
//            User user = new User();
//            user.setUsername(registerRequest.getUsername());
//            user.setPassword(cypherText);
//            user.setRole(role);
//            user.setActivated(true);
//            UserDetail userDetail = new UserDetail();
//            userDetail.setUser(user);
//            userDetail.setPhoneNumber(registerRequest.getPhoneNumber());
//            userDetail.setFullName(registerRequest.getFullName());
//            userDetail.setGender(registerRequest.getGender());
//            userDetail.setEmail(registerRequest.getEmail());
//            userRepository.save(user);
//            userDetailRepository.save(userDetail);
//            Cart cart = new Cart();
//            cart.setUser(user);
//            cartRepository.save(cart);
        } catch (Exception e) {
            responseDTO.setMessage("Register failure!");
            return responseDTO;
        }
//        responseDTO.setMessage(Constant.REGISTER_SUCCESS);
        return responseDTO;
    }
}
