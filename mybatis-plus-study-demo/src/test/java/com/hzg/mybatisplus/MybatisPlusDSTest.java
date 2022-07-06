package com.hzg.mybatisplus;

import com.hzg.mybatisplus.pojo.Product;
import com.hzg.mybatisplus.pojo.User;
import com.hzg.mybatisplus.service.ProductService;
import com.hzg.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Package: com.hzg.mybatisplus
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-29 12:51
 */
@SpringBootTest
public class MybatisPlusDSTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @Test
    public void test() {
        List<User> userList = userService.list();
        userList.forEach(System.out::println);

        List<Product> productList = productService.list();
        productList.forEach(System.out::println);
    }

}
