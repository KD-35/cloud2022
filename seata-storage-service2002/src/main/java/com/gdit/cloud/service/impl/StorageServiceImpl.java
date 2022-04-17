package com.gdit.cloud.service.impl;

import com.gdit.cloud.dao.StorageDao;
import com.gdit.cloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao storageDao;


    @Override
    public int decrease(Long ProductId, Integer count) {
        return  storageDao.decrease(ProductId,count);

    }
}
