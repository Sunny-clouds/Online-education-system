package com.onik.eduspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestPaperDto {

    private Long id;
    private String title;
    private Long courseId;
    private Integer totalScore;
    private Long createUser;
}
