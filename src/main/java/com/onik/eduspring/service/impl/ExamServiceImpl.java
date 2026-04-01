package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.ExamDto;
import com.onik.eduspring.entity.Exam;
import com.onik.eduspring.mapper.ExamMapper;
import com.onik.eduspring.service.ExamService;
import com.onik.eduspring.vo.ExamVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    /**
     * 根据活动id获取考试信息
     * @param activityId
     * @return
     */
    @Override
    public ExamVo getExamByActivityId(Long activityId) {
        return examMapper.getExamByActivityId(activityId);
    }

    /**
     * 新增考试信息
     * @param examDto
     */
    @Override
    public void saveExam(ExamDto examDto) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(examDto,exam);
        if (exam.getMaxAttempts() > 1){
            exam.setAllowRetake(exam.getMaxAttempts() - 1);
        }
        exam.setAllowRetake(0);
        exam.setCreateTime(LocalDateTime.now());
        examMapper.saveExam(exam);
    }

    /**
     * 修改考试信息
     * @param examDto
     */
    @Override
    public void updateExam(ExamDto examDto) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(examDto,exam);
        if (exam.getMaxAttempts() > 1){
            exam.setAllowRetake(exam.getMaxAttempts() - 1);
        }
        exam.setAllowRetake(0);
        examMapper.updateExam(exam);
    }
}
