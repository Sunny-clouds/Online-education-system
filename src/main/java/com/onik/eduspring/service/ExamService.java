package com.onik.eduspring.service;

import com.onik.eduspring.dto.ExamDto;
import com.onik.eduspring.vo.ExamVo;

public interface ExamService {

    /**
     * 根据活动id获取考试信息
     * @param activityId
     * @return
     */
    ExamVo getExamByActivityId(Long activityId);

    /**
     * 新增考试信息
     * @param examDto
     */
    void saveExam(ExamDto examDto);

    /**
     * 修改考试信息
     * @param examDto
     */
    void updateExam(ExamDto examDto);
}
