package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeWork {

    private Long id;
    private Long activityId;
    private Long courseId;
    private Long studentId;
    private String content;
    private LocalDateTime submitTime;

}
