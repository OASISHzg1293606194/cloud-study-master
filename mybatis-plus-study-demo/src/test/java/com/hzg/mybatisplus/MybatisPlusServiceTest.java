package com.hzg.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzg.mybatisplus.pojo.User;
import com.hzg.mybatisplus.service.UserService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.hzg.mybatisplus
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-24 14:20
 */
@SpringBootTest
public class MybatisPlusServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void testCount() {
        // SELECT COUNT( * ) FROM yh_user
        long count = userService.count();
        System.out.println("count:" + count);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getName, "oasis");
        System.out.println(userService.count(queryWrapper));
    }

    @Test
    public void testSaveBatch() {
        // INSERT INTO yh_user ( biz_id, name, age, email, created_by, create_time, updated_by, update_time, deleted ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String tempName = "oasis";
            User user = new User();
            user.setBizId(RandomUtils.nextLong());
            user.setName(tempName + i);
            user.setAge(i);
            user.setEmail(tempName + i + "@email.com");
            user.creatorData();
            list.add(user);
        }
        userService.saveBatch(list);
    }

    @Test
    public void testDelete() {
        // 设置deleted为逻辑删除字段

        // UPDATE yh_user SET deleted=1 WHERE deleted=0 AND (id > ?)
        // QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // queryWrapper.lambda().gt(User::getId, 28L);
        // boolean removeResult = userService.remove(queryWrapper);
        // System.out.println("removeResult:" + removeResult);

        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE deleted=0
        List<User> userList = userService.list();
        userList.forEach(System.out::println);
    }

}
