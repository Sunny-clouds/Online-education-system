package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProgressVo {

    private Long id;
    private Long studentId;
    private Long videoId;
    private Long courseId;
    private Long currentTime;
    private Double progress;
    private Integer status;
}
