package com.hzg.mybatisplus;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hzg.mybatisplus.constants.BaseConstants;
import com.hzg.mybatisplus.mapper.UserMapper;
import com.hzg.mybatisplus.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Package: com.hzg.mybatisplus
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-25 10:56
 */
@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testQueryWrapper() {
        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE deleted=0 AND (name LIKE ? AND age BETWEEN ? AND ? AND email = ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(User::getName, "a")
                .between(User::getAge, 20, 30)
                .eq(User::getEmail, "");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testQueryWrapperOrderBy() {
        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByDesc(User::getAge)
                .orderByAsc(User::getId);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testQueryWrapperDelete() {
        // UPDATE yh_user SET deleted=1 WHERE deleted=0 AND (email = ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getEmail, "");
        int deleteResult = userMapper.delete(queryWrapper);
        System.out.println("deleteResult:" + deleteResult);
    }

    @Test
    public void testQueryWrapperUpdate() {
        // UPDATE yh_user SET name=?, updated_by=?, update_time=? WHERE deleted=0 AND (age = ?)
        User user = new User();
        user.setName("fiction-name");
        user.updatorData();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getAge, 20);
        int updateResult = userMapper.update(user, queryWrapper);
        System.out.println("updateResult:" + updateResult);
    }

    @Test
    public void testWherePriority() {
        // UPDATE yh_user SET name=?, updated_by=?, update_time=? WHERE deleted=0 AND (name LIKE ? AND (age > ? OR email = ?))
        // 条件优先级
        // lambda表达式中的条件优先执行
        User user = new User();
        user.setName("fnew-name");
        user.updatorData();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .like(User::getName, "f")
                .and(i -> i.gt(User::getAge, 20).or().eq(User::getEmail, ""));
        int updateResult = userMapper.update(user, updateWrapper);
        System.out.println("updateResult:" + updateResult);
    }

    @Test
    public void testSelectSomeField() {
        // select部分字段
        // SELECT biz_id,name FROM yh_user WHERE deleted=0 AND (name LIKE ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .select(User::getBizId, User::getName)
                .like(User::getName, "new");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSubquery() {
        // 子查询示例
        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE deleted=0 AND (biz_id IN (SELECT biz_id FROM `yh_user` WHERE age > 20))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .inSql(User::getBizId, "SELECT biz_id FROM `yh_user` WHERE age > 20");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testUpdateWrapper() {
        // UPDATE yh_user SET name=?,updated_by=?,update_time=? WHERE deleted=0 AND (age = ?)
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(User::getAge, 20)
                .set(User::getName, "fake-name")
                .set(User::getUpdatedBy, BaseConstants.SYS_USERNAME)
                .set(User::getUpdateTime, LocalDateTime.now());
        int updateResult = userMapper.update(null, updateWrapper);
        System.out.println("updateResult:" + updateResult);
    }

    @Test
    public void testSimulateRealScene() {
        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE deleted=0 AND (name LIKE ? AND age >= ? AND age <= ?)
        User user = new User();
        user.setName("洲");
        user.setAgeBegin(22);
        user.setAgeEnd(26);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(user.getName())) {
            queryWrapper.lambda().like(User::getName, user.getName());
        }
        if (ObjectUtil.isNotNull(user.getAgeBegin())) {
            queryWrapper.lambda().ge(User::getAge, user.getAgeBegin());
        }
        if (ObjectUtil.isNotNull(user.getAgeEnd())) {
            queryWrapper.lambda().le(User::getAge, user.getAgeEnd());
        }
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testCondition() {
        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE deleted=0 AND (age >= ? AND age <= ?)
        User user = new User();
        user.setName("");
        user.setAgeBegin(22);
        user.setAgeEnd(26);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotBlank(user.getName()), User::getName, user.getName())
                .ge(ObjectUtil.isNotNull(user.getAgeBegin()), User::getAge, user.getAgeBegin())
                .le(ObjectUtil.isNotNull(user.getAgeEnd()), User::getAge, user.getAgeEnd());
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testLambdaQueryWrapper() {
        User user = new User();
        user.setName("");
        user.setAgeBegin(22);
        user.setAgeEnd(26);

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(user.getName()), User::getName, user.getName())
                .ge(ObjectUtil.isNotNull(user.getAgeBegin()), User::getAge, user.getAgeBegin())
                .le(ObjectUtil.isNotNull(user.getAgeEnd()), User::getAge, user.getAgeEnd());
        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testLambdaUpdateWrapper() {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getAge, 20)
                .set(User::getName, "fakes-name")
                .set(User::getUpdatedBy, BaseConstants.SYS_USERNAME)
                .set(User::getUpdateTime, LocalDateTime.now());
        int updateResult = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println("updateResult:" + updateResult);
    }

}
