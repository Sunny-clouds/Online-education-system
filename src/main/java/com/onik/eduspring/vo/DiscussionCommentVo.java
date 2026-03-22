package com.onik.eduspring.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionCommentVo {

    private Long id;
    private String nickname;
    private String avatar;
    private Long parentId;
    private String content;
    private Long likeCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
    // 子评论
    private List<DiscussionCommentVo> children;

}
