package com.hzg.cloud.service.impl;

import com.hzg.cloud.dao.OrderDao;
import com.hzg.cloud.domain.Order;
import com.hzg.cloud.feign.AccountFeignClient;
import com.hzg.cloud.feign.StorageFeignClient;
import com.hzg.cloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Package: com.hzg.cloud.service.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-02 21:04
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageFeignClient storageFeignClient;
    @Autowired
    private AccountFeignClient accountFeignClient;


    @Override
    @GlobalTransactional(name = "seata-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("===================>创建订单");
        orderDao.createOrder(order);

        log.info("===================>feign调用库存服务-减少库存-begin");
        storageFeignClient.decreaseStorage(order.getProductId(), order.getCount());
        log.info("===================>feign调用库存服务-减少库存-end");

        log.info("===================>feign调用账户服务-减少余额-begin");
        accountFeignClient.decreaseAccount(order.getUserId(), order.getMoney());
        log.info("===================>feign调用账户服务-减少余额-end");

        log.info("===================>修改订单状态-已完成");
        orderDao.updateToComplete(order.getUserId(), Order.OrderStatusEnum.WAIT_PAY.getCode());

        log.info("===================>ALL END");
    }

}
