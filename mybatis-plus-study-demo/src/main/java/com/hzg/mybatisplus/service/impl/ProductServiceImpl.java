package com.hzg.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzg.mybatisplus.mapper.ProductMapper;
import com.hzg.mybatisplus.pojo.Product;
import com.hzg.mybatisplus.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @Package: com.hzg.mybatisplus.service.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-29 12:43
 */
@DS("slave_1")
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
