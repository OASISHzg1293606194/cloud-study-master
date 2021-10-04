package com.hzg.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Package: com.hzg.cloud.domain
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 23:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;
    private Long userId;
    private BigDecimal total;
    private BigDecimal used;
    private BigDecimal residue;

}
