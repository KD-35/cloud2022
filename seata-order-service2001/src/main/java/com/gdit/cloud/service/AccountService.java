package com.gdit.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
@Component
@FeignClient(value = "seate-account-service")
public interface AccountService {

    @GetMapping("/account/decrease")
    public int decrease(@RequestParam("userId")Long userId, @RequestParam("money")BigDecimal money);

}
