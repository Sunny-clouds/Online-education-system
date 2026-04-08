package com.onik.eduspring.service;

import com.onik.eduspring.dto.ExamDto;
import com.onik.eduspring.result.Result;
import com.onik.eduspring.vo.ExamVo;

public interface ExamService {

    /**
     * 根据活动id获取考试信息
     * @param bizId
     * @return
     */
    ExamVo getExamByBizId(Long bizId);

    /**
     * 新增考试信息
     * @param examDto
     */
    Long saveExam(ExamDto examDto);

    /**
     * 修改考试信息
     * @param examDto
     */
    Result updateExam(ExamDto examDto);
}
