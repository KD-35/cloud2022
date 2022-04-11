package com.gdit.cloud.service;

import entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> qryAllPayment();

    Payment qryPaymentById(int id);
}
