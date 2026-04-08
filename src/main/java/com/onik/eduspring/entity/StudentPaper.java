package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPaper {

    private Long id;
    private Long studentId;
    private Long activityId;
    private Long paperId;
    private Integer attempt;
    private Integer totalScore;
    private LocalDateTime startTime;
    private LocalDateTime submitTime;

}
