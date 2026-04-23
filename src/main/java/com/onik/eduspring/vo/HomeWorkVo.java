package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeWorkVo {

    private Long id;
    private String studentName;
    private String content;
    private LocalDateTime submitTime;
}
