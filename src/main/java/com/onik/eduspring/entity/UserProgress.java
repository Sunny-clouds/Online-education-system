package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 学生观看视频的进度
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProgress {

    private Long id;
    private Long studentId;
    private Long videoId;
    private Double progress;
    private LocalDateTime lastWatchTime;
}
