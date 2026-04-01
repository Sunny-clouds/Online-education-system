package com.onik.eduspring.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 试卷信息
 */
public class TestPaperVo {
    private Long id;
    private Integer type;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Double paperScore;
}
