package com.unifasservice.repository;


import com.unifasservice.entity.SaleVoucher;
import com.unifasservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SaleVoucherRepository  extends JpaRepository<SaleVoucher, Long> {
}