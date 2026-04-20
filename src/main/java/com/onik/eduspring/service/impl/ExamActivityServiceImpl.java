package com.onik.eduspring.service.impl;

import com.onik.eduspring.dto.ActivityDto;
import com.onik.eduspring.dto.PublishExamDto;
import com.onik.eduspring.service.ActivityService;
import com.onik.eduspring.service.ExamActivityService;
import com.onik.eduspring.service.ExamService;
import com.onik.eduspring.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamActivityServiceImpl implements ExamActivityService {

    @Autowired
    private TestPaperService testPaperService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ActivityService activityService;

    /**
     * 创建考试
     * @param dto
     */
    @Transactional
    public void publishExamActivity(PublishExamDto dto) {
        // 1、创建试卷
        Long paperId = testPaperService.saveTestPaper(dto.getTestPaperDto());
        dto.getTestPaperDto().setId(paperId);
        // 2、自动组卷
        testPaperService.AutoGenerateTestPaper(dto.getTestPaperDto());
        // 3、创建考试
        dto.getExamDto().setPaperId(paperId);
        Long examId = examService.saveExam(dto.getExamDto());
        dto.getActivityDto().setBizId(examId);
        // 4、发布活动
        ActivityDto activityDto = dto.getActivityDto();
        activityService.save(activityDto);
    }
}
