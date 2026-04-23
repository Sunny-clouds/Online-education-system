package com.onik.eduspring.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnswerVo {

    private Long questionId;
    private Integer type;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private Integer totalA;
    private Integer totalB;
    private Integer totalC;
    private Integer totalD;
    private Integer totalTrue;
    private Integer totalFalse;
}
