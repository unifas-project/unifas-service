package com.unifasservice.dto.payload.response;

import lombok.*;


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
    private String contact;
    private String receiver;
    private String isDefault;
}
