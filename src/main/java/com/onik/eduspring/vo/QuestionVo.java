package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVo {

    private Long id;
    private Long type;
    private String title;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String analysis;
    private Double score;
    private Integer difficulty;
    private String nickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}