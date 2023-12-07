package com.unifasservice.dto.payload.request;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDetailRequest {
   private String username;
   private String role;
   private String fullName;
   private Date dateOfBirth;

}
