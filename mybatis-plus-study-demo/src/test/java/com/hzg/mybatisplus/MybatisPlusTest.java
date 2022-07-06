package com.hzg.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.gson.Gson;
import com.hzg.cloud.utils.IdGeneratorSnowflake;
import com.hzg.mybatisplus.constants.BaseConstants;
import com.hzg.mybatisplus.constants.enums.DeletedEnum;
import com.hzg.mybatisplus.mapper.UserMapper;
import com.hzg.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.hzg.mybatisplus
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-23 13:58
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectList() {
        Gson gson = new Gson();
        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE id=?
        // User user = userMapper.selectById(22L);
        // System.out.println(gson.toJson(user));

        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE id IN ( ? , ? , ? )
        // List<Long> idList = new ArrayList<>();
        // idList.add(21L);
        // idList.add(22L);
        // idList.add(28L);
        // List<User> userList = userMapper.selectBatchIds(idList);
        // System.out.println(userList.size());
        // userList.forEach(System.out::println);

        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE name = ? AND age = ?
        // Map<String, Object> columnMap = new HashMap<>();
        // columnMap.put("name", "ocean");
        // columnMap.put("age", 22);
        // List<User> users = userMapper.selectByMap(columnMap);
        // users.forEach(System.out::println);

        // SELECT id,biz_id,name,age,email,created_by,create_time,updated_by,update_time,deleted FROM yh_user WHERE (deleted = ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getDeleted, DeletedEnum.UN_DELETED.getCode());
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList.size());
        userList.forEach(user -> {
            System.out.println(gson.toJson(user));
        });
    }

    @Test
    public void testInsert() {
        IdGeneratorSnowflake snowflake = new IdGeneratorSnowflake();
        // INSERT INTO yh_user ( biz_id, name, age, email, created_by, create_time, updated_by, update_time, deleted ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )
        User user = new User();
        user.setBizId(snowflake.snowflakeId());
        user.setName("will");
        user.setAge(22);
        user.setEmail("will@email.com");
        user.creatorData();
        int insertResult = userMapper.insert(user);
        System.out.println("insertResult:" + insertResult);
        System.out.println("userId:" + user.getId());
    }

    @Test
    public void testSelect() {
        // 自定义查询-对象转为map: 属性名=mapKey，属性值=mapValue
        Map<String, Object> map = userMapper.selectMapById(22L);
        System.out.println(map);
        // {update_time=2022-03-24T09:50:16, deleted=0, create_time=2022-03-24T09:50:16, name=ocean, updated_by=admin, id=22, biz_id=1506810597709316096, created_by=admin, age=22, email=ocean@email.com}
    }

    @Test
    public void testUpdate() {
        // UPDATE yh_user SET name=?, age=?, email=? WHERE id=?
        // User user = new User();
        // user.setId(28L);
        // user.setName("will-rename");
        // user.setAge(26);
        // user.setEmail("will-rename@email.com");
        // int updateByIdResult = userMapper.updateById(user);
        // System.out.println("updateByIdResult:" + updateByIdResult);

        // UPDATE yh_user SET name=?,age=?,email=?,updated_by=?,update_time=? WHERE (biz_id = ?)
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(User::getBizId, 1506486903417995266L)
                .set(User::getName, "绿洲")
                .set(User::getAge, 24)
                .set(User::getEmail, "1293606194@qq.com")
                .set(User::getUpdatedBy, BaseConstants.SYS_USERNAME)
                .set(User::getUpdateTime, LocalDateTime.now());
        int updateResult = userMapper.update(null, updateWrapper);
        System.out.println("updateResult:" + updateResult);
    }

    @Test
    public void testDelete() {
        // DELETE FROM yh_user WHERE id=?
        // int deleteByIdResult = userMapper.deleteById(23);
        // System.out.println("deleteByIdResult:" + deleteByIdResult);

        // DELETE FROM yh_user WHERE name = ? AND age = ?
        // Map<String, Object> columnMap = new HashMap<>();
        // columnMap.put("name", "will");
        // columnMap.put("age", 22);
        // int deleteByMapResult = userMapper.deleteByMap(columnMap);
        // System.out.println("deleteByMapResult:" + deleteByMapResult);

        // DELETE FROM yh_user WHERE id IN ( ? , ? , ? )
        List<Long> idList = new ArrayList<>();
        idList.add(25L);
        idList.add(26L);
        idList.add(27L);
        int deleteBatchIdsResult = userMapper.deleteBatchIds(idList);
        System.out.println("deleteBatchIdsResult:" + deleteBatchIdsResult);
    }

}
