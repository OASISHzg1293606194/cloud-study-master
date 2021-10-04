package com.hzg.cloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.hzg.cloud.dao
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 19:43
 */
@Mapper
public interface StorageDao {

    void decreaseStorage(@Param("productId") Long productId, @Param("count") Integer count);

}
