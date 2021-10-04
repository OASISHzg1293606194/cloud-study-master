package com.hzg.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Package: com.hzg.cloud.domain
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-02 20:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    /**
     * 订单状态：0：创建中；1：已完结
     */
    private Integer status;

    public enum OrderStatusEnum {
        /**
         * 待支付
         */
        WAIT_PAY(0),

        /**
         * 已完成
         */
        COMPLETE(1);

        @Getter
        @Setter
        private Integer code;

        OrderStatusEnum(Integer code) {
            this.code = code;
        }
    }

}
