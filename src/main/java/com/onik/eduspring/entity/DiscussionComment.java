package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 评论实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionComment {

    private Long id;
    private Long postId;
    private Long userId;
    private Long parentId;
    private String content;
    private Long likeCount;
    private Integer status;
    private LocalDateTime createTime;
}
