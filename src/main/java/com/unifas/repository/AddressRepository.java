package com.unifas.repository;

import com.unifas.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;



public interface AddressRepository  extends JpaRepository<Address, Long> {
}
