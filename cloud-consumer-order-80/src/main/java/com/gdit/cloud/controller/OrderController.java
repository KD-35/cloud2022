package com.gdit.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OrderController {

    private String URL = "http://CLOUD-PROVIDER-PAYMENT";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/getAll")
    public String qryAllPayment() {
        return restTemplate.getForObject(URL + "/payment/getAll", String.class);

    }

    @GetMapping("/consumer/payment/get/{id}")
    public String qryPaymentById(@PathVariable("id") int id) {
         return   restTemplate.getForObject(URL+"/payment/get/"+id,String.class);
    }
}
