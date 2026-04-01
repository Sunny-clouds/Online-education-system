package com.onik.eduspring.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsCourseVo {

    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private Long progress;
    private Integer totalScore;
}
