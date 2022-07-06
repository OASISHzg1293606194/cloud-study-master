package com.hzg.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzg.mybatisplus.mapper.UserMapper;
import com.hzg.mybatisplus.pojo.User;
import com.hzg.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Package: com.hzg.mybatisplus.service.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-24 14:17
 */
@DS("master")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
