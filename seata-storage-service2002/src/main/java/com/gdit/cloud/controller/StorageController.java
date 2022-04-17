package com.gdit.cloud.controller;

import com.gdit.cloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/storage/decrease")
    public int decrease(Long productId, Integer count) {
        log.info("productId:"+productId);
        return storageService.decrease(productId,count);
    }

}
