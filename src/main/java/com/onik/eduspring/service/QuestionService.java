package com.onik.eduspring.service;

import com.onik.eduspring.dto.ExamTitleDto;
import com.onik.eduspring.vo.QuestionVo;

import java.util.List;

public interface QuestionService {


    /**
     * 新增题目
     * @return
     */
    void saveExamTitle(ExamTitleDto examTitleDto);

    /**
     * 修改题目
     * @param examTitleDto
     */
    void updateExamTitle(ExamTitleDto examTitleDto);

    /**
     * 获取题库所有题目信息
     * @return
     */
    List<QuestionVo> getAllExamTitle();

    /**
     * 根据id删除题库题目信息
     * @param id
     */
    void delExamTitle(Long id);
}
