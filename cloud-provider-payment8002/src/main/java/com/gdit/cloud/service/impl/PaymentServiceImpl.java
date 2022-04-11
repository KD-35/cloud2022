package com.gdit.cloud.service.impl;

import com.gdit.cloud.mapper.PaymentMapper;
import com.gdit.cloud.service.PaymentService;
import entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<Payment> qryAllPayment() {
        return paymentMapper.qryAllPayment();
    }

    @Override
    public Payment qryPaymentById(int id) {
        return paymentMapper.qryPaymentById(id);
    }
}
