package com.unifasservice.service.impl;

import com.unifasservice.converter.AddressConverter;
import com.unifasservice.dto.payload.CommonResponse;
import com.unifasservice.dto.payload.request.AddressRequest;
import com.unifasservice.dto.payload.response.AddressResponse;
import com.unifasservice.entity.Address;
import com.unifasservice.entity.Order;
import com.unifasservice.entity.User;
import com.unifasservice.repository.AddressRepository;
import com.unifasservice.repository.UserRepository;
import com.unifasservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    private final AddressConverter addressConverter;


    @Override
    public CommonResponse createCommonResponse(Object data, String message, HttpStatus statusCode) {
        return CommonResponse
                .builder()
                .data(data)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    @Override
    public CommonResponse getUserAddressList(long userId) {
        List<Address> addressList = addressRepository.findByUserId(userId);
        for (int i = 0; i < addressList.size(); i++) {
            if ("T".equals(addressList.get(i).getIsDefault())) {
                Address subAddress = addressList.get(i);
                addressList.remove(addressList.get(i));
                addressList.add(0, subAddress);
                break;
            }
        }


        List<AddressResponse> addressResponseList = addressConverter.convertAddressEntityListToResponseList(addressList);

        return createCommonResponse(addressResponseList, "Get address success", HttpStatus.OK);
    }

    @Override
    public CommonResponse addNewAddress(long userId, AddressRequest addressRequest) {
        User userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Address address = addressConverter.convertAddressRequestToEntity(addressRequest);
        List<Address> addressList = userEntity.getAddressList();

        String isDefaultAddress = address.getIsDefault();
        if ("T".equals(isDefaultAddress)) {
            for (Address subAddress : addressList){
                if ("T".equals(subAddress.getIsDefault())){
                    subAddress.setIsDefault("F");
                    break;
                }
            }
            address.setUser(userEntity);
            address.setOrderList(Collections.singletonList(new Order()));
            addressRepository.save(address);
            return createCommonResponse(false, "Add new address successfully", HttpStatus.OK);
        } else {
            if (addressList.size()==0){
                address.setIsDefault("T");
            }

            address.setUser(userEntity);
            address.setOrderList(Collections.singletonList(new Order()));
            addressRepository.save(address);
            return createCommonResponse(false, "Add new address successfully", HttpStatus.OK);
        }
    }

    @Override
    public CommonResponse getUserAddressForEdit(long addressId) {
       Address address = addressRepository.findById(addressId).orElseThrow(
               () -> new IllegalArgumentException("Address not found")
       );
       AddressResponse addressResponse = addressConverter.convertAddressEntityToResponse(address);
       return createCommonResponse(addressResponse,"Get address for edit successfully", HttpStatus.OK);
    }
}





