package com.onik.eduspring.util;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 操作类
 * 负责将文件上传到阿里云对象存储
 */
@Component
@Slf4j
public class AliyunOSSOperator {

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private String bucketName;

    @Value("${aliyun.endpoint}")
    private String endpoint;

    /**
     * 上传文件到阿里云 OSS
     * @param inputStream 文件输入流
     * @param originalFilename 原始文件名
     * @return 文件访问 URL
     */
    public String upload(InputStream inputStream, String originalFilename, String type) {
        // 校验文件名是否为空
        if (originalFilename == null || originalFilename.trim().isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        // 获取文件扩展名并生成唯一文件名，防止覆盖
        String extension = getFileExtension(originalFilename).toLowerCase();
        String folder = getFolderByType(type);
        String objectName = folder + "/" + generateFileName() + extension;
        try {
            // 4. 构建上传请求（使用流式上传，避免内存占用）
            PutObjectRequest putObjectRequest = PutObjectRequest.newBuilder()
                    .bucket(bucketName)                  // 存储桶名称
                    .key(objectName)                     // 文件路径（对象名）
                    .body(BinaryData.fromStream(inputStream)) // 使用输入流上传
                    .build();
            // 执行上传
            ossClient.putObject(putObjectRequest);
            log.info("文件上传成功：{}/{}", bucketName, objectName);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败：" + e.getMessage(), e);
        }
        // 返回文件的完整访问 URL
        return buildFileUrl(bucketName, endpoint, objectName);
    }

    /**
     * 获取文件扩展名
     * @param filename 文件名
     * @return 文件扩展名（包含点）
     */
    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 生成唯一文件名（UUID）
     * @return 不含横线的 UUID 字符串
     */
    private String generateFileName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 构建文件访问 URL
     * @param bucketName 存储桶名称
     * @param endpoint OSS 服务端点
     * @param objectName 对象名称
     * @return 完整的文件访问 URL
     */
    private String buildFileUrl(String bucketName, String endpoint, String objectName) {
        String cleanEndpoint = endpoint.replace("https://", "").replace("http://", "");
        return "https://" + bucketName + "." + cleanEndpoint + "/" + objectName;
    }

    /**
     * 根据文件类型返回存储目录
     */
    private String getFolderByType(String type) {
        switch (type) {
            case "image":
                return "images";
            case "video":
                return "videos";
            case "doc":
                return "docs";
            default:
                throw new RuntimeException("未知文件类型：" + type);
        }
    }
}
