package com.gdit.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "seate-storage-service")
public interface StorageService {
    @GetMapping("/storage/decrease")
    public int decrease(@RequestParam("productId") Long productId, @RequestParam("count")Integer count);
}
