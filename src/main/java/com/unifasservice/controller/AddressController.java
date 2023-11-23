package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddressRequest;
import com.unifasservice.entity.User;
import com.unifasservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@CrossOrigin("*")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/address/{user-id}")
    private ResponseEntity<CommonResponse> getUserAddressList (@PathVariable("user-id") long userId){
               CommonResponse commonResponse = addressService.getUserAddressList(userId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @PostMapping("/address/{user-id}")
    private ResponseEntity<CommonResponse> addNewAddress(@PathVariable("user-id") long userId, @RequestBody AddressRequest addressRequest){
        CommonResponse commonResponse = addressService.addNewAddress(userId,addressRequest);
        return null;
    }

}
