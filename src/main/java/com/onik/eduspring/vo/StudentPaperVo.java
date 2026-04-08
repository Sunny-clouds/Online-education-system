package com.onik.eduspring.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPaperVo {

    private Long id;
    private String nickName;
    private Integer attempt;
    private Integer totalScore;
}
