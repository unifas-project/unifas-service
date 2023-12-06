package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.UserDetailRequest;
import com.unifasservice.dto.payload.request.UserPasswordRequest;
import com.unifasservice.dto.payload.response.UserDetailResponse;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;
   private final UserRepository userRepository;

   @GetMapping("/profile")
   public ResponseEntity<?> getProfileUser() {
      try {
         String username = SecurityContextHolder.getContext().getAuthentication().getName();
         UserDetailResponse updatedUser = userService.getUserByUsername(username);

         CommonResponse commonResponse = new CommonResponse();
         commonResponse.setData(updatedUser);
         commonResponse.setMessage("Get profile for user success !");
         commonResponse.setStatusCode(HttpStatus.OK);
         return new ResponseEntity<>(commonResponse, HttpStatus.OK);
      } catch (Exception e) {
         CommonResponse commonResponse = new CommonResponse();
         commonResponse.setData(null);
         commonResponse.setMessage("Get profile for user fail");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
      }
   }

   @PutMapping("/update-profile")
   public ResponseEntity<?> updateByUsername(@RequestBody UserDetailRequest userDetailRequest) {
      try {
         String username = SecurityContextHolder.getContext().getAuthentication().getName();

         UserDetailResponse updatedUser = userService.updateUserByUsername(username, userDetailRequest);

         CommonResponse commonResponse = new CommonResponse();
         commonResponse.setData(updatedUser);
         commonResponse.setMessage("Update profile for user success !");
         commonResponse.setStatusCode(HttpStatus.OK);

         return new ResponseEntity<>(commonResponse, HttpStatus.OK);
      } catch (Exception e) {
         CommonResponse commonResponse = new CommonResponse();
         commonResponse.setData(null);
         commonResponse.setMessage("Update profile for user fail");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);

         return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
      }
   }

   @PutMapping("/user-password")
   public ResponseEntity<?> changePasswordUser(
           @Valid @RequestBody UserPasswordRequest userPasswordRequest,
           BindingResult bindingResult) {

      if (bindingResult.hasErrors()) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                 .body("Validation error: " + bindingResult.getAllErrors().get(0).getDefaultMessage());
      }

      try {
         String username = SecurityContextHolder.getContext().getAuthentication().getName();

         UserDetailResponse updatedUser = userService.changePasswordByUsername(username, userPasswordRequest);

         CommonResponse commonResponse = new CommonResponse();
         commonResponse.setData(updatedUser);
         commonResponse.setMessage("Update password for user success !");
         commonResponse.setStatusCode(HttpStatus.OK);

         return new ResponseEntity<>(commonResponse, HttpStatus.OK);
      } catch (Exception e) {
         CommonResponse commonResponse = new CommonResponse();
         commonResponse.setMessage("Update password for user fail.");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);

         return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
      }
   }
}













