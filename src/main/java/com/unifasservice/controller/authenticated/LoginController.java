package com.unifasservice.controller.authenticated;

import com.example.unifasservice.dto.request.RQRegisterDTO;
import com.example.unifasservice.dto.response.RPRegisterDTO;
import com.example.unifasservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<RPRegisterDTO> registerUser(@RequestBody RQRegisterDTO registerRequestDTO) {
        RPRegisterDTO responseDto = userService.register(registerRequestDTO);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }
}
