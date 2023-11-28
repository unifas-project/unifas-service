package com.unifasservice.dto.payload.response;

import lombok.*;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private long id;
    private String street;
    private String ward;
    private String district;
    private String city;
    private long contact;
    private String receiver;
    private boolean isDefault;


}
