package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.ExamDto;
import com.onik.eduspring.entity.Exam;
import com.onik.eduspring.mapper.ActivityMapper;
import com.onik.eduspring.mapper.ExamMapper;
import com.onik.eduspring.result.Result;
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
    @Autowired
    private ActivityMapper activityMapper;

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
        if (exam.getMaxAttempt() > 1){
            exam.setAllowRetake(1);
        }else {
            exam.setAllowRetake(0);
        }
        exam.setCreateTime(LocalDateTime.now());
        examMapper.saveExam(exam);
    }

    /**
     * 修改考试信息
     * @param examDto
     */
    @Override
    public Result updateExam(ExamDto examDto) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = activityMapper.getStartTime(examDto.getActivityId());
        if (now.isBefore(startTime)) {
            // 未开始
            Exam exam = new Exam();
            BeanUtils.copyProperties(examDto,exam);
            if (exam.getMaxAttempt() > 1){
                exam.setAllowRetake(1);
            }else {
                exam.setAllowRetake(0);
            }
            examMapper.updateExam(exam);
            return Result.success();
        }
        return Result.error("考试进行中，不能修改");
    }
}
