package com.onik.eduspring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 考试实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    private Long id;
    private Long activityId;
    private Long paperId;
    private Integer duration;
    private Integer totalScore;
    private Integer allowRetake;
    private Integer maxAttempts;
    private Integer showResults;
    private LocalDateTime createTime;
}
