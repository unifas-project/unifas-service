package com.unifasservice.converter;

import com.unifasservice.dto.payload.request.UserDetailRequest;
import com.unifasservice.dto.payload.response.UserDetailResponse;
import com.unifasservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDetailConverter {
   public User toEntity(UserDetailRequest dto) {
      User user = new User();
      user.setUsername(dto.getUsername());
      user.setRole(dto.getRole());
      user.setFullName((dto.getFullName()));
      user.setDateOfBirth((dto.getDateOfBirth()));
      return user;
   }


   public UserDetailResponse toDto(User entity) {
      UserDetailResponse dto = new UserDetailResponse();
      dto.setId(entity.getId());
      dto.setUsername(entity.getUsername());
      dto.setEmail(entity.getEmail());
      dto.setFullName(entity.getFullName());
      dto.setDateOfBirth((entity.getDateOfBirth()));
      return dto;
   }
}
