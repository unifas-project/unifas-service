package com.unifasservice.dto.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserPasswordRequest {
   @NotBlank(message = "Current password is required")
   private String currentPassword;

   @NotBlank(message = "New password is required")
   @Size(min = 8, message = "New password must be at least 8 characters long")
   @Pattern.List({
           @Pattern(regexp = ".*[a-z].*", message = "New password must contain at least one lowercase letter"),
           @Pattern(regexp = ".*[A-Z].*", message = "New password must contain at least one uppercase letter"),
           @Pattern(regexp = ".*\\d.*", message = "New password must contain at least one digit"),
           @Pattern(regexp = ".*[!@#$%^&*()-+=].*", message = "New password must contain at least one special character")})
   private String newPassword;

   @NotBlank(message = "Configmation password is required")
   private String confirmationPassword;

}
