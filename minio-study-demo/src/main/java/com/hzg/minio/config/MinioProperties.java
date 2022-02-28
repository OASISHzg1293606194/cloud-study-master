package com.hzg.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Package: com.hzg.minio.config
 * @Description: minio配置信息Properties
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-09 18:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;

}
