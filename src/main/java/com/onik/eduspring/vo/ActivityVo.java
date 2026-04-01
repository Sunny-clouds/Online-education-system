package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityVo {
    private Long id;
    private String title;
    private Double score;
    private LocalDateTime createTime;
    private Integer commentCount;
    private Integer type;
    private Integer status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
