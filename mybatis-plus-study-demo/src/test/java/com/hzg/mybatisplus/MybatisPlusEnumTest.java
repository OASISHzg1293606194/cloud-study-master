package com.hzg.mybatisplus;

import cn.hutool.core.util.RandomUtil;
import com.hzg.mybatisplus.mapper.UserMapper;
import com.hzg.mybatisplus.pojo.User;
import com.hzg.mybatisplus.pojo.enums.SexEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Package: com.hzg.mybatisplus
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-28 23:18
 */
@SpringBootTest
public class MybatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test() {
        User user = new User();
        user.setBizId(RandomUtil.randomLong());
        user.setName("pink_sky");
        user.setSex(SexEnum.FEMALE);
        user.setAge(23);
        user.setEmail("pinkk_skyyyy@email.com");
        user.creatorData();
        // int insertResult = userMapper.insert(user);
        // System.out.println("insertResult:" + insertResult);

        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        SexEnum sexEnum = userList.get(userList.size() - 1).getSex();
        System.out.println(sexEnum.getCode());
    }

}
