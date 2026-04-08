package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnswer {

    private Long id;
    private Long studentPaperId;
    private Long studentId;
    private Long questionId;
    private String studentAnswer;
    private String correctAnswer;
    private Integer score;
    private Integer isCorrect;
}
