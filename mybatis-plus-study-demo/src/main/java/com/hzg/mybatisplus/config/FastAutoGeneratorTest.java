package com.hzg.mybatisplus.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Package: com.hzg.mybatisplus.config
 * @Description: MybatisPlus代码生成器
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-28 23:38
 */
public class FastAutoGeneratorTest {

    /**
     * 数据库连接信息
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3308/mybatis_plus?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    /**
     * 作者信息
     */
    private static final String AUTHOR_NAME = "HuangZhiGao";
    private static final String OUTPUT_DIR = "D://mybatis-plus-generate";

    /**
     * 包名信息
     */
    private static final String PARENT_PACKAGE_PATH = "com.hzg.mybatisplus.simples";
    private static final String PARENT_MODULE_NAME = "business";

    /**
     * 表名信息
     */
    private static final String TABLE_NAME = "t_product";
    private static final String[] TABLE_PREFIX = new String[]{"t_", "yh_"};

    public static void main(String[] args) {
        FastAutoGenerator.create(DB_URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR_NAME) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(OUTPUT_DIR); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(PARENT_PACKAGE_PATH) // 设置父包名
                            .moduleName(PARENT_MODULE_NAME) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, OUTPUT_DIR)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAME) // 设置需要生成的表名
                            .addTablePrefix(TABLE_PREFIX); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
