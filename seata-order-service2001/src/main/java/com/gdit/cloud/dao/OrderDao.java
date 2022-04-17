package com.gdit.cloud.dao;

import com.gdit.cloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //新增订单
    int create(Order order);

    //修改订单状态
    int updateOrderByUserId(@Param("userId") Long userId,@Param("status") Integer status);
}
