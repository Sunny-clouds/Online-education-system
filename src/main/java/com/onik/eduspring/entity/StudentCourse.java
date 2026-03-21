package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourse {


    private Long id;
    private Long studentId;
    private Long courseId;
    private Long teacherId;
    private LocalDateTime selectTime;
    private Long status;
    private Long progress;

}
