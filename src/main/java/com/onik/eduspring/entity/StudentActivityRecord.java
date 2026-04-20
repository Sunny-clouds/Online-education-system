package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 统一学生活动记录实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentActivityRecord {

    private Long id;
    private Long studentId;
    private Long activityId;
    private Long courseId;
    private Long score;
    private LocalDateTime submitTime;
}
