package com.hzg.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.hzg.mybatisplus.mapper.ProductMapper;
import com.hzg.mybatisplus.mapper.UserMapper;
import com.hzg.mybatisplus.pojo.Product;
import com.hzg.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @Package: com.hzg.mybatisplus
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-28 14:33
 */
@SpringBootTest
public class MybatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;


    @Test
    public void testPage() {
        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE deleted=0 ORDER BY create_time DESC LIMIT ?,?
        // LIMIT n,m    n = (currentPage - 1) * pageSize
        Page<User> page = new Page<>(2, 4);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(User::getCreateTime);
        userMapper.selectPage(page, queryWrapper);
        System.out.println((new Gson()).toJson(page));

        System.out.println("当前页---------current:" + page.getCurrent());
        System.out.println("页大小---------size:" + page.getSize());
        System.out.println("是否有上一页----hasPrevious:" + page.hasPrevious());
        System.out.println("是否有下一页----hasNext:" + page.hasNext());
        System.out.println("总页数---------pages:" + page.getPages());
        System.out.println("总数据---------total:" + page.getTotal());
        System.out.println("当前页数据集合--records:" + page.getRecords());
    }

    @Test
    public void testCustomizePage() {
        Page<User> page = new Page<>(1, 2);
        User user = new User();
        user.setName("fakes-name");
        user.setAgeBegin(25);
        user.setAgeEnd(30);
        userMapper.queryListByParam(page, user);
        System.out.println(page.getRecords());
    }

    @Test
    public void testOptimisticLock2() {
        /**
         * 乐观锁插件
         * <p/>
         *
         * 支持的数据类型只有: int、Integer、long、Long、Date、Timestamp、LocalDateTime
         * 整数类型下newVersion=oldVersion+1
         * newVersion会回写到entity中
         * 仅支持updateById(entity)与update(entity, wrapper)方法
         * 在update(entity, wrapper)方法下,wrapper不能复用!!!
         */
        // com.hzg.mybatisplus.config.MybatisPlusConfig中配置OptimisticLockerInnerInterceptor前后对比
        // 模拟修改冲突
        QueryWrapper<Product> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.lambda().eq(Product::getBizId, 1506486903417995268L);
        Product product1 = productMapper.selectOne(queryWrapper1);
        System.out.println("product1查询价格：" + product1.getPrice());

        QueryWrapper<Product> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().eq(Product::getBizId, 1506486903417995268L);
        Product product2 = productMapper.selectOne(queryWrapper2);
        System.out.println("product2查询价格：" + product2.getPrice());


        // 两个人同时查询同时修改
        // 第一个人查询出来要将产品价格抬高20块钱 预期：37+20
        UpdateWrapper<Product> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.lambda().eq(Product::getBizId, 1506486903417995268L);
        product1.setPrice(product1.getPrice().add(new BigDecimal(20)));
        int updateResult1 = productMapper.update(product1, updateWrapper1);
        System.out.println("updateResult1:" + updateResult1);

        // 第二个人查询出来要将产品价格降低10块钱 预期：37+20-10
        UpdateWrapper<Product> updateWrapper2 = new UpdateWrapper<>();
        updateWrapper2.lambda().eq(Product::getBizId, 1506486903417995268L);
        product2.setPrice(product2.getPrice().subtract(new BigDecimal(10)));
        int updateResult2 = productMapper.update(product2, updateWrapper2);
        System.out.println("updateResult2:" + updateResult2);
        if (updateResult2 == 0) {
            // 失败重试
            QueryWrapper<Product> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.lambda().eq(Product::getBizId, 1506486903417995268L);
            Product product3 = productMapper.selectOne(queryWrapper3);
            product3.setPrice(product3.getPrice().subtract(new BigDecimal(10)));
            UpdateWrapper<Product> updateWrapper3 = new UpdateWrapper<>();
            updateWrapper3.lambda().eq(Product::getBizId, 1506486903417995268L);
            int updateResult3 = productMapper.update(product3, updateWrapper3);
            System.out.println("updateResult3:" + updateResult3);
        }

        // 没加乐观锁--最后的价格预期应该是47，但是结果实际却是27(37-10覆盖了37+20)
    }

}
