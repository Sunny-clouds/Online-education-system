package com.onik.eduspring.dto;

import com.onik.eduspring.entity.StudentPaper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishStudentExamDto {

    private List<StudentAnswerDto> studentAnswerDtos;
    private StudentPaper studentPaper;
}
