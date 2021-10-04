package com.hzg.cloud.dao;

import com.hzg.cloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.hzg.cloud.dao
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-02 21:11
 */
@Mapper
public interface OrderDao {

    /**
     * 新建订单
     */
    void createOrder(Order order);

    /**
     * 修改订单状态，从零改为1
     */
    void updateToComplete(@Param("userId") Long userId, @Param("status") Integer status);

}
