package com.gdit.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdit.cloud.service.PaymentService;
import entity.CommonResult;
import entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Autowired
    private RestTemplate restTemplate;

    public static final String SERVICE_URL="http://nacos-provider-payment";



    @GetMapping(value = "/consumer/paymentSQL/{id}")
//    @SentinelResource(value = "fallback")
//    @SentinelResource(value = "fallback",fallback = "handlerFallback",exceptionsToIgnore = IllegalAccessException.class)//fallback只负责业务异常
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责控制台配置违规
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "handlerFallback",exceptionsToIgnore = IllegalAccessException.class)//blockHandler只负责控制台配置违规
    public CommonResult<Payment> paymentSQL(@PathVariable("id") int id){
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);
        if(id==4){
            throw new IllegalArgumentException("IllegalArgumentException,非法用户。");
        }else if(result.getData()==null){
            throw new NullPointerException("NullPointerException,该ID没有对应的记录。");
        }

        return result;
    }
    public CommonResult<Payment> handlerFallback(@PathVariable("id")int id,Throwable throwable){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444,"兜底方法异常handlerFallback(╥﹏╥)o"+throwable.getMessage(),payment);
    }
    public CommonResult<Payment> blockHandler(@PathVariable("id")int id, BlockException e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444,"handlerFallback(╥﹏╥)o"+e.getMessage(),payment);
    }

    //--------------Openfeign
    @Resource
    private PaymentService paymentService;
    @GetMapping("/consumer/openFeign/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id){
        return  paymentService.paymentSQL(id);
    }
}
