package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.UserResponse;
import com.unifasservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
