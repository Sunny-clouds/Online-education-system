package com.onik.eduspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDto {

    private Long id;
    private Long activityId;
    private Long paperId;
    private Integer duration;
    private Integer totalScore;
    private Integer allowRetake;
    private Integer maxAttempts;
    private Integer showResults;
}
