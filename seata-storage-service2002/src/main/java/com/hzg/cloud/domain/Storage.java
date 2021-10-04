package com.hzg.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.hzg.cloud.domain
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 19:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {

    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;

}
