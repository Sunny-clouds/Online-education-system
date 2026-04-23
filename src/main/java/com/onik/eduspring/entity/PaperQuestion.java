package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 试卷-题目关联实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperQuestion {

    private Long id;
    private Long paperId;
    private Long questionId;
    private int score;
    private Integer sort;

}
