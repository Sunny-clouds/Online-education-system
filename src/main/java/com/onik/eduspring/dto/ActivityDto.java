package com.onik.eduspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {

    private Long id;
    private Long bizId;
    private Long courseId;
    private String title;
    private Long score;
    private Integer type;
    private Integer status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
