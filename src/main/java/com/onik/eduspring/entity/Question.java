package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 题目实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private Long id;
    private Long type;
    private Long courseId;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String analysis;
    private Integer score;
    private Integer difficulty;
    private Long createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
