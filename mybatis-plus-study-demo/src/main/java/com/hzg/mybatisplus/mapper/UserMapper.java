package com.hzg.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzg.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Package: com.hzg.mybatisplus.mapper
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-23 13:50
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户信息Map
     * <p/>
     * {update_time=2022-03-24T09:50:16, deleted=0, create_time=2022-03-24T09:50:16, name=ocean, updated_by=admin, id=22, biz_id=1506810597709316096, created_by=admin, age=22, email=ocean@email.com}
     * <p/>
     *
     * @param id
     * @return java.util.Map
     * @author HuangZhiGao
     * @date 2022/3/24/024 11:00
     */
    Map<String, Object> selectMapById(@Param("id") Long id);

    /**
     * 自定义分页查询
     * <p/>
     *
     * @param page 分页参数--“必须要在第一个参数的位置”
     * @param user 查询参数
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.hzg.mybatisplus.pojo.User>
     * @author HuangZhiGao
     * @date 2022/3/28/028 15:13
     */
    Page<User> queryListByParam(Page<User> page, @Param("queryParam") User user);

}
