package com.hzg.minio.demo;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

/**
 * @Package: com.hzg.minio.demo
 * @Description: 测试MinIO文件上传
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-09 17:34
 */
public class FileUploader {

    private static final String MINIO_SERVER_URL = "http://192.168.213.225:9000";
    private static final String MINIO_SERVER_USERNAME = "admin";
    private static final String MINIO_SERVER_PASSWORD = "oasishzg";

    public static void main(String[] args) {
        try {
            // 创建MinioClient
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(MINIO_SERVER_URL)
                    .credentials(MINIO_SERVER_USERNAME, MINIO_SERVER_PASSWORD)
                    .build();

            String bucketName = "java-demo-test-bucket";
            // 判断bucket是否存在
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!exists) {
                // 不存在则创建bucket
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // 上传文件
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketName)
                            .object("dilibare06-alias.jpg")
                            .filename("E:\\MinIO\\dilibare06.jpg")
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("====================文件上传结束====================");
    }
}
