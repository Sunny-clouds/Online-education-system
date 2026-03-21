package com.onik.eduspring.strategy.impl;

import com.onik.eduspring.strategy.FileStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("videoFileStrategy")
public class VideoFileStrategy implements FileStrategy {
    @Override
    public void validate(MultipartFile file) {
        String filename = file.getOriginalFilename().toLowerCase();
        String suffix = filename.substring(filename.lastIndexOf("."));

        if (!suffix.equals(".mp4")) {
            throw new RuntimeException("只支持MP4视频");
        }

        if (file.getSize() > 200 * 1024 * 1024) {
            throw new RuntimeException("视频不能超过200MB");
        }
    }
}
