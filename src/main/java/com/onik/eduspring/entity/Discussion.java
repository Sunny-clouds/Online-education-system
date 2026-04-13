package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 帖子实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discussion {

    private Long id;
    private Long userId;
    private Long courseId;
    private String title;
    private String content;
    private Long likeCount;
    private Long isTop;
    private LocalDateTime createTime;
}
