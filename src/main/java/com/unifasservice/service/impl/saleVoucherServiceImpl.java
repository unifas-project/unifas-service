package com.unifasservice.service.impl;

import com.unifasservice.converter.SaleVoucherConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.SaleVoucherRequest;
import com.unifasservice.dto.payload.response.SaleVoucherResponse;
import com.unifasservice.entity.SaleVoucher;
import com.unifasservice.repository.SaleVoucherRepository;
import com.unifasservice.service.SaleVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class saleVoucherServiceImpl implements SaleVoucherService {

    private final SaleVoucherRepository saleVoucherRepository;

    private final SaleVoucherConverter saleVoucherConverter;

    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    @Override
    public CommonResponse checkVoucherValid(String code) {
        SaleVoucher saleVoucherEntity = saleVoucherRepository.findByCode(code.toUpperCase());
        if (saleVoucherEntity == null) {
            throw new IllegalArgumentException("Voucher is invalid or expired. Try again");
        }

        SaleVoucherResponse saleVoucherResponse = saleVoucherConverter.convertSaleVoucherEntityToResponse(saleVoucherEntity);
        return createCommonResponse(saleVoucherResponse,"Valid voucher",HttpStatus.OK);
    }

    @Override
    public CommonResponse createSaleVoucher(SaleVoucherRequest saleVoucherRequest) {
        String saleVoucherCode = saleVoucherRequest.getCode();
        SaleVoucher saleVoucherEntity = saleVoucherRepository.findByCode(saleVoucherCode);
        if (saleVoucherEntity != null){
            if (!saleVoucherEntity.isDeleted()){
            throw new IllegalArgumentException("This voucher have existed");
            }
        }

        SaleVoucher saleVoucher = saleVoucherConverter.convertSaleVoucherRequestToEntity(saleVoucherRequest);
        saleVoucherRepository.save(saleVoucher);
        return createCommonResponse(false,"Create sale voucher successfully", HttpStatus.OK);
    }


    @Override
    public CommonResponse getAllVouchers(int page) {

        Pageable pageable = PageRequest.of(page,5);
        Page<SaleVoucher> saleVoucherPage = saleVoucherRepository.findAll(pageable);
        Page<SaleVoucherResponse> saleVoucherResponsePage = saleVoucherConverter.convertPageEntityToResponse(saleVoucherPage);
        return createCommonResponse(saleVoucherResponsePage,"Get all sale voucher successfully",HttpStatus.OK);
    }

    @Override
    public CommonResponse deleteSaleVoucher(long id) {
        SaleVoucher saleVoucherEntity = saleVoucherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale voucher not found"));
        saleVoucherEntity.setDeleted(true);
        saleVoucherRepository.save(saleVoucherEntity);
        return createCommonResponse(false,"Delete sale voucher successfully",HttpStatus.OK);
    }

}
