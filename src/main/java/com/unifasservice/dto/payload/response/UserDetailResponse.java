package com.unifasservice.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
   private long id;
   private String username;
   private String role;
   private String email;
   private String fullName;
   private Date dateOfBirth;

}
