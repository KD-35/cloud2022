package com.gdit.cloud.service.impl;

import com.gdit.cloud.dao.AccountDao;
import com.gdit.cloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Override
    public int decrease(Long userId, BigDecimal money) {
        return accountDao.decrease(userId, money);
    }
}
