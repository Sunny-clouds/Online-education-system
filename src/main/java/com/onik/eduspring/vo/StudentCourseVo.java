package com.onik.eduspring.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCourseVo {
    private Long id;
    private String userName;
    private String title;
    private Long courseId;
    private String teacherName;
    private LocalDateTime selectTime;
    private Long status;
    private Long progress;
}
