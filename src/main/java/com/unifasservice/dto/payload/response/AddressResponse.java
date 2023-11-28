package com.unifasservice.dto.payload.response;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
import lombok.*;

>>>>>>> 5166cebadfdf6d7b3debaed328e9588b4f98c0e6

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
