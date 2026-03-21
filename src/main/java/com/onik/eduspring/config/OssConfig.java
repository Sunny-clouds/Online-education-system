package com.onik.eduspring.config;

import com.aliyun.sdk.service.oss2.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfig {

    @Value("${aliyun.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.endpoint}")
    private String endpoint;

    @Value("${aliyun.bucket-name}")
    private String bucketName;

    @Bean
    public OSSClient ossClient() {
        return OSSClient.newBuilder()
                .credentialsProvider(new com.aliyun.sdk.service.oss2.credentials.StaticCredentialsProvider(accessKeyId, accessKeySecret))
                .region(endpoint.replace("oss-", "").replace(".aliyuncs.com", ""))
                .build();
    }
    @Bean
    public String bucketName() {
        return bucketName;
    }
}
