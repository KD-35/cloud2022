package com.gdit.cloud.controller;

import entity.CommonResult;
import entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {
    @Autowired
    private RestTemplate restTemplate;

    public static final String SERVICE_URL="http://nacos-provider-payment";

    //--------------Openfeign
   /* @Autowired
    private PaymentService paymentService;*/

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class);
    }

}
