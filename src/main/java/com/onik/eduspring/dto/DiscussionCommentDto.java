package com.onik.eduspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscussionCommentDto {

    private Long id;
    private Long postId;
    private Long userId;
    private Long parentId;
    private String content;
}
