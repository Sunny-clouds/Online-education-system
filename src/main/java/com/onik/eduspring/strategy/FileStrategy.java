package com.onik.eduspring.strategy;


import org.springframework.web.multipart.MultipartFile;

/**
 * 文件策略接口
 */
public interface FileStrategy {

    /**
     * 文件校验
     */
    void validate(MultipartFile file);
}