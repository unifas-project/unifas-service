package com.unifas.service.impl;

import com.unifas.converter.UserLoginConverter;
import com.unifas.dto.request.UserLoginRequestDTO;
import com.unifas.dto.response.UserLoginResponseDTO;
import com.unifas.entity.User;
import com.unifas.repository.UserRepository;
import com.unifas.service.IUserService;
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