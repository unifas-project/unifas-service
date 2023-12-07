package com.unifasservice.controller;

import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.OrderRequest;
import com.unifasservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/user/{user-id}/order")
    public ResponseEntity<CommonResponse> createOrder(@RequestBody OrderRequest orderRequest, @PathVariable("user-id") long userId){
        try {
            CommonResponse commonResponse = orderService.createOrder(userId, orderRequest);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.NOT_FOUND);
            return new  ResponseEntity<>(commonResponse,HttpStatus.OK);
        }catch (Exception e){
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new  ResponseEntity<>(commonResponse,HttpStatus.OK);
        }

    }

    @GetMapping("/user/{user-id}/order")
    public ResponseEntity<CommonResponse> getAllOrderLineToCreateOrder(@PathVariable("user-id") long id){
        try {
            CommonResponse commonResponse = orderService.getAllCartItemToCreateOrder(id);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }catch (Exception e){
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new  ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
    }


    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

}
