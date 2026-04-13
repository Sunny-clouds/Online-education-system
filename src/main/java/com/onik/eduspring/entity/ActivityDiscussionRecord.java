package com.onik.eduspring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动讨论记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDiscussionRecord {
    private Long id;
    private Long activityId;
    private Long studentId;
    private LocalDateTime submitTime;
    private String content;
}
