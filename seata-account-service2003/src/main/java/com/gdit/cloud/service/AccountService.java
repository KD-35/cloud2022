package com.gdit.cloud.service;


import java.math.BigDecimal;

public interface AccountService {

    int decrease(Long userId, BigDecimal money);
}
