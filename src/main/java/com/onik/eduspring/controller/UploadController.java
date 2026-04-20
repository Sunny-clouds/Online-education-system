package com.onik.eduspring.controller;

import com.onik.eduspring.factory.FileStrategyFactory;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.strategy.FileStrategy;
import com.onik.eduspring.util.AliyunOSSOperator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件上传控制器
 */
@RestController
@Slf4j
@RequestMapping("/api/upload")
@Tag(name = "文件上传接口")
public class UploadController {

    @Autowired
    private AliyunOSSOperator ossOperator;
    @Autowired
    private FileStrategyFactory fileStrategyFactory;
    /**
     * 文件上传接口（支持图片 / 视频 / 文档）
     *
     * @param file 上传文件
     * @param type 文件类型（image / video / doc）
     * @return 文件访问URL
     */
    @PostMapping("/upload")
    @Operation(summary = "文件上传接口")
    public Result upload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        try {
            // 1. 判空
            if (file == null || file.isEmpty()) {
                return Result.error("文件不能为空");
            }
            String filename = file.getOriginalFilename();
            if (filename == null) {
                return Result.error("文件名异常");
            }
            // 2. 获取策略
            FileStrategy strategy = fileStrategyFactory.getStrategy(type);
            // 3. 执行校验
            strategy.validate(file);
            // 4. 流式上传
            InputStream inputStream = file.getInputStream();
            String url = ossOperator.upload(inputStream, file.getOriginalFilename(),type);
            log.info("文件上传成功：{}", url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error(e.getMessage());
        }
    }
}
