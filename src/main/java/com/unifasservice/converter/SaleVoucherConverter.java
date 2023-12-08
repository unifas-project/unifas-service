package com.unifasservice.converter;

import com.unifasservice.dto.payload.request.SaleVoucherRequest;
import com.unifasservice.dto.payload.response.SaleVoucherResponse;
import com.unifasservice.entity.SaleVoucher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleVoucherConverter {

    public SaleVoucher convertSaleVoucherRequestToEntity (SaleVoucherRequest saleVoucherRequest){
        return SaleVoucher
                .builder()
                .id(saleVoucherRequest.getId())
                .code(saleVoucherRequest.getCode().toUpperCase())
                .discount(saleVoucherRequest.getDiscount())
                .isDeleted(false)
                .build();
    }

    public SaleVoucherResponse convertSaleVoucherEntityToResponse (SaleVoucher saleVoucher){
        return SaleVoucherResponse
                .builder()
                .id(saleVoucher.getId())
                .code(saleVoucher.getCode())
                .discount(saleVoucher.getDiscount())
                .build();
    }

    public List<SaleVoucherResponse> convertListSaleVoucherEntityToListResponse(List<SaleVoucher> saleVoucherList) {
        return saleVoucherList.stream().map(this::convertSaleVoucherEntityToResponse).collect(Collectors.toList());
    }

    public Page<SaleVoucherResponse> convertPageEntityToResponse(Page<SaleVoucher> saleVoucherPage) {
        return saleVoucherPage.map(this::convertSaleVoucherEntityToResponse);
    }
}
