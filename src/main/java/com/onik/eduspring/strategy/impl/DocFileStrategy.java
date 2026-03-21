package com.onik.eduspring.strategy.impl;

import com.onik.eduspring.strategy.FileStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("docFileStrategy")
public class DocFileStrategy implements FileStrategy {
    @Override
    public void validate(MultipartFile file) {
        String filename = file.getOriginalFilename().toLowerCase();
        String suffix = filename.substring(filename.lastIndexOf("."));

        if (!(suffix.equals(".pdf") || suffix.equals(".doc") || suffix.equals(".docx"))) {
            throw new RuntimeException("文档格式错误");
        }

        if (file.getSize() > 20 * 1024 * 1024) {
            throw new RuntimeException("文档不能超过20MB");
        }
    }
}
