package com.unifasservice.service;


import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.SaleVoucherRequest;
import org.springframework.data.domain.Pageable;

public interface SaleVoucherService {
    CommonResponse checkVoucherValid(String code);

    CommonResponse createSaleVoucher(SaleVoucherRequest saleVoucherRequest);


    CommonResponse getAllVouchers(int page);

    CommonResponse deleteSaleVoucher(long id);

}
