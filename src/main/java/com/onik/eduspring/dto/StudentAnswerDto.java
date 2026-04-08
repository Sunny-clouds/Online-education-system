package com.onik.eduspring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnswerDto {

    private Long id;
    private Long studentPaperId;
    private Long studentId;
    private Long questionId;
    private String studentAnswer;
    private String correctAnswer;
    private Integer score;
    private Integer countScore;
}
