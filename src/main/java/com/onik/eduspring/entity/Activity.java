package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    private Long id;
    private Long courseId;
    private String title;
    private Integer type;
    private Integer status;
    private Double score;
    private LocalDateTime createTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
