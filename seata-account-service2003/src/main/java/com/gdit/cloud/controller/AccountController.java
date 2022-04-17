package com.gdit.cloud.controller;

import com.gdit.cloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account/decrease")
    public int decrease(Long userId, BigDecimal money) {
        return accountService.decrease(userId, money);
    }
}
