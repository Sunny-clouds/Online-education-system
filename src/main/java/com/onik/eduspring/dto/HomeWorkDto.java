package com.onik.eduspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeWorkDto {

    private Long activityId;
    private Long courseId;
    private Long studentId;
    private String content;
    private LocalDateTime submitTime;

}
