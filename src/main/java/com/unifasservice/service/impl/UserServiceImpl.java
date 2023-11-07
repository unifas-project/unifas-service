package com.unifasservice.service.impl;

import com.unifasservice.converter.UserLoginConverter;
import com.unifasservice.dto.request.UserLoginRequestDTO;
import com.unifasservice.dto.response.UserLoginResponseDTO;
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
}