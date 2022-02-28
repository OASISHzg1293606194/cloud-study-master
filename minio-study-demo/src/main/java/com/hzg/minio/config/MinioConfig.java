package com.hzg.minio.config;

import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Package: com.hzg.minio.config
 * @Description: minio配置信息类
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-09 18:54
 */
@Configuration
@ConfigurationProperties(prefix = "minio")
@EnableConfigurationProperties({MinioProperties.class})
@ConditionalOnProperty(prefix = "minio", name = "enabled", havingValue = "true")
public class MinioConfig {

    // @ConditionalOnProperty(prefix = "minio", name = "enabled", havingValue = "true")
    // prefix为配置文件中的前缀
    // name为配置的名字
    // havingValue是与配置的值对比值，当两个值相同返回true，配置类生效

    @Resource
    private MinioProperties minioProperties;


    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        return minioClient;
    }

    @PostConstruct
    public void init() {
        System.out.println("================================");
        System.out.println(minioProperties.toString());
        System.out.println("================================");
    }

}
