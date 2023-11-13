package com.unifasservice.controller.authenticated;


import com.unifasservice.dto.request.UserLoginRequestDto;
import com.unifasservice.dto.response.UserLoginResponseDto;
import com.unifasservice.dto.request.UserRegisterRequestDto;
import com.unifasservice.dto.response.UserRegisterResponseDto;
import com.unifasservice.service.UserService;
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
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto loginRequestDTO,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            UserLoginResponseDto loginResponseDTO = userService.login(loginRequestDTO);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping(value = "/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto userRegisterRequestDTO) {
        UserRegisterResponseDto responseDto = userService.register(userRegisterRequestDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
