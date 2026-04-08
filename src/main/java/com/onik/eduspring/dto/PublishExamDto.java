package com.onik.eduspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishExamDto {

    private TestPaperDto testPaperDto;
    private ExamDto examDto;
    private ActivityDto activityDto;
}
