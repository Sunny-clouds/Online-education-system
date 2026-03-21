package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreVo {

    private String userName;
    private String title;
    private String examName;
    private Double usualScore;
    private Double examScore;
    private Double totalScore;
    private String gradeLevel;
    private String teacherName;
    private String remark;

}
