package com.gdit.cloud.mapper;

import entity.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    List<Payment> qryAllPayment();

    Payment qryPaymentById(int id);
}
