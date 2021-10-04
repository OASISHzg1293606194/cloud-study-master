package com.hzg.cloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Package: com.hzg.cloud.dao
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 23:19
 */
@Mapper
public interface AccountDao {

    void decreaseAccount(@Param("userId") Long userId, @Param("money") BigDecimal money);

}
