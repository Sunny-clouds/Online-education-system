package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseVo {

    private Long id;
    private String title;
    private String description;
    private String cover;
    private String teacherName;
    private LocalDateTime createTime;
}
