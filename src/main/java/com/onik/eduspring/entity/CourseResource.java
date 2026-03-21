package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 课程资源实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResource {

    private Long id;
    private Long courseId;
    private String name;
    private String url;
    private String coverUrl;
    private Long size;
    private Long duration;
    private Long userId;
    private LocalDateTime createTime;
}
