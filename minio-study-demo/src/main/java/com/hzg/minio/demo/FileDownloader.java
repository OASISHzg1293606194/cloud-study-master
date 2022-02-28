package com.hzg.minio.demo;

import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;

/**
 * @Package: com.hzg.minio.demo
 * @Description: 测试MinIO文件下载
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-09 17:34
 */
public class FileDownloader {

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
            // 上传文件
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(bucketName)
                            .object("dilibare06-alias.jpg")
                            .filename("E:\\MinIO\\dilibare06-download.jpg")
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("====================文件下载结束====================");
    }
}
