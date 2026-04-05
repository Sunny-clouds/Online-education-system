package com.onik.eduspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCommentVo {
    private Long id;
    private String nickname;
    private String avatar;
    private String content;
    private String submitTime;
}
