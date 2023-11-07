package com.unifas.controller.authenticated;


import com.unifas.dto.request.UserLoginRequestDTO;
import com.unifas.dto.response.UserLoginResponseDTO;
import com.unifas.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    private IUserService iUserService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO loginRequestDTO,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            UserLoginResponseDTO loginResponseDTO = iUserService.login(loginRequestDTO);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}