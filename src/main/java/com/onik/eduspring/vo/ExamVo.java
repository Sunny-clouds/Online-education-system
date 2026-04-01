package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamVo {

    private Long id;
    private Long paperId;
    private String title;
    private String duration;
    private Integer totalScore;
    private Integer allowRetake;
    private Integer maxAttempt;
    private Integer showResult;
}
