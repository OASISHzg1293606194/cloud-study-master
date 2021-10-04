package com.hzg.cloud.service.impl;

import com.hzg.cloud.dao.StorageDao;
import com.hzg.cloud.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Package: com.hzg.cloud.service.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 22:54
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    @Override
    public void decreaseStorage(Long productId, Integer count) {
        LOGGER.info("===================>扣减库存-begin");
        storageDao.decreaseStorage(productId, count);
        LOGGER.info("===================>扣减库存-end");
    }

}
