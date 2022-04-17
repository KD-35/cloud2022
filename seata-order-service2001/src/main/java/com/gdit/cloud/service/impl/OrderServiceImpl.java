package com.gdit.cloud.service.impl;

import com.gdit.cloud.dao.OrderDao;
import com.gdit.cloud.domain.Order;
import com.gdit.cloud.service.AccountService;
import com.gdit.cloud.service.OrderService;
import com.gdit.cloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    @Override
    public int create(Order order) {
        log.info("开始新建订单");
        int res = orderDao.create(order);
        log.info("订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("订单微服务结束调用库存");
        log.info("订单微服务调用余额系统");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("订单微服务调用余额系统结束");
        log.info("刷新订单状态");
        int res1 = orderDao.updateOrderByUserId(order.getUserId(), order.getStatus());
        return res1;
    }

}
