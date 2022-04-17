package com.gdit.cloud.controller;

import com.gdit.cloud.domain.Order;
import com.gdit.cloud.service.OrderService;
import entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        int res = orderService.create(order);
        if(res==1){
            return new CommonResult(200,"订单创建成功。");
        }
        return null;
    }
}
