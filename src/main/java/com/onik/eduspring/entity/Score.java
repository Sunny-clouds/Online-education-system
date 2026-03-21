package com.onik.eduspring.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 成绩实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {

    private Long id;
    private Long studentId;
    private Long courseId;
    private String examName;
    private Double usualScore;
    private Double examScore;
    private Double totalScore;
    private String gradeLevel;
    private Long teacherId;
    private String remark;
}
