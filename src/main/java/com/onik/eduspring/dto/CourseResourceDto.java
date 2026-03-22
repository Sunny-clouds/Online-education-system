package com.onik.eduspring.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResourceDto {

    private Long courseId;
    private String name;
    private String url;
    private String type;
    private Long size;
    private Long duration;
    private Long userId;
}
