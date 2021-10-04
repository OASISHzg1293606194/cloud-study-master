package com.hzg.cloud.service;

/**
 * @Package: com.hzg.cloud.service
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 22:53
 */
public interface StorageService {

    void decreaseStorage(Long productId, Integer count);

}
