package com.onik.eduspring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDiscussionRecord {
    private Long id;
    private Long activityId;
    private Long studentId;
    private LocalDateTime submitTime;
    private Double score;
    private String content;
}
