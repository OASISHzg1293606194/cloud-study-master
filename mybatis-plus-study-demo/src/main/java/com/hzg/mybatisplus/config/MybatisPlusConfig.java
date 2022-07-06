package com.hzg.mybatisplus.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.hzg.mybatisplus.config
 * @Description: MybatisPlus配置
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-23 17:26
 */
@Configuration
@MapperScan("com.hzg.mybatisplus.mapper")
public class MybatisPlusConfig {

    /**
     * MybatisPlus拦截器
     * <p/>
     *
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @author HuangZhiGao
     * @date 2022/3/28/028 14:25
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }

}
