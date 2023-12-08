package com.unifasservice.controller;


import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.SaleVoucherRequest;
import com.unifasservice.service.SaleVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@CrossOrigin("*")
public class SaleVoucherController {

    private final SaleVoucherService saleVoucherService;


    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    @GetMapping("/sale-vouchers")
    public ResponseEntity<CommonResponse> getAllSaleVouchers(@RequestParam int page){
        try {
            CommonResponse commonResponse = saleVoucherService.getAllVouchers(page);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }catch (Exception e){
            CommonResponse commonResponse = createCommonResponse(false, "Something went wrong", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
    }




    @PostMapping("/sale-voucher")
    public ResponseEntity<CommonResponse> createSaleVoucher (@RequestBody SaleVoucherRequest saleVoucherRequest){
        try{
            CommonResponse commonResponse = saleVoucherService.createSaleVoucher(saleVoucherRequest);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }catch (Exception e){
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
    }

    @PostMapping("/sale-voucher/check")
    public ResponseEntity<CommonResponse> checkVoucherValid(@RequestBody SaleVoucherRequest saleVoucherRequest){
        try {
            String code = saleVoucherRequest.getCode();
            CommonResponse commonResponse = saleVoucherService.checkVoucherValid(code);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }catch (Exception e){
            CommonResponse commonResponse = createCommonResponse(false,e.getMessage(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }

    }

    @DeleteMapping("/sale-voucher/{id}")
    public ResponseEntity<CommonResponse> deleteSaleVoucher(@PathVariable long id){
        try {
            CommonResponse commonResponse = saleVoucherService.deleteSaleVoucher(id);
            return new  ResponseEntity<>(commonResponse,HttpStatus.OK);
        }catch (Exception e) {
            CommonResponse commonResponse = createCommonResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
    }



}
