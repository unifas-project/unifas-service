package com.unifasservice.controller.authenticated;


import com.unifasservice.dto.request.UserRegisterRequestDTO;
import com.unifasservice.dto.response.UserRegisterResponseDTO;
import com.unifasservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private IUserService userService;
    @PostMapping(value = "/register-user")
    public ResponseEntity<UserRegisterResponseDTO> registerUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        UserRegisterResponseDTO responseDto = userService.register(userRegisterRequestDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
