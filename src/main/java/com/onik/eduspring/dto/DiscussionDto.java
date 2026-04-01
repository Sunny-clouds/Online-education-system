package com.onik.eduspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionDto {

    private Long userId;
    private Long courseId;
    private String title;
    private String content;
}
