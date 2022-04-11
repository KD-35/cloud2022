package com.gdit.cloud.controller;

import com.gdit.cloud.service.PaymentService;
import entity.CommonResult;
import entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

        @GetMapping("/payment/getAll")
        public CommonResult<List<Payment>> qryAllPayment(){

            List<Payment> payments = paymentService.qryAllPayment();
            if(payments.size()>0){
                return new CommonResult<>(200,"查询成功！",payments);
            }
            return new CommonResult<>(400,"查询失敗！",null);
        }

        @GetMapping("/payment/get/{id}")
        public CommonResult<Payment> qryPaymentById(@PathVariable("id") int id){
            Payment payment = paymentService.qryPaymentById(id);
            if(payment!=null){
                return new CommonResult<Payment>(200,"查询成功！",payment);
            }
            return new CommonResult<Payment>(400,"查询失敗！",null);
        }

}
