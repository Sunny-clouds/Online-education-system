package com.onik.eduspring.strategy.impl;

import com.onik.eduspring.strategy.FileStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("imageFileStrategy")
public class ImageFileStrategy implements FileStrategy {
    @Override
    public void validate(MultipartFile file) {
        String filename = file.getOriginalFilename().toLowerCase();
        String suffix = filename.substring(filename.lastIndexOf("."));

        if (!(suffix.equals(".jpg") || suffix.equals(".png") || suffix.equals(".jpeg"))) {
            throw new RuntimeException("图片格式错误");
        }

        if (file.getSize() > 2 * 1024 * 1024) {
            throw new RuntimeException("图片不能超过2MB");
        }
    }
}
