package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 试卷实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestPaper {

    private Long id;
    private String title;
    private Long courseId;
    private Integer totalScore;
    private Long createUser;
    private LocalDateTime createTime;
}
